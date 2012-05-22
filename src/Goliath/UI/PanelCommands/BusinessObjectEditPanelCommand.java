/* ========================================================
 * BusinessObjectEditPanelCommand.java
 *
 * Author:      kenmchugh
 * Created:     Dec 24, 2010, 4:38:04 PM
 *
 * Description
 * --------------------------------------------------------
 * General Class Description.
 *
 * Change Log
 * --------------------------------------------------------
 * Init.Date        Ref.            Description
 * --------------------------------------------------------
 *
 * ===================================================== */

package Goliath.UI.PanelCommands;

import Goliath.Applications.Application;
import Goliath.Collections.HashTable;
import Goliath.Collections.List;
import Goliath.Data.BusinessObjects.BusinessObject;
import Goliath.Data.BusinessObjects.DynamicDataObject;
import Goliath.Delegate;
import Goliath.DynamicCode.Java;
import Goliath.Event;
import Goliath.Interfaces.UI.Controls.IButton;
import Goliath.Interfaces.UI.Controls.IControl;
import Goliath.Interfaces.UI.Controls.IWindow;
import Goliath.UI.Controls.Button;
import Goliath.UI.Controls.Layouts.GridLayoutManager;
import Goliath.UI.Skin.Skin;
import Goliath.UI.Skin.SkinManager;
import Goliath.UI.Constants.UIEventType;
import Goliath.UI.Views.BusinessObjectEditView;
import Goliath.UI.Views.DynamicDataObjectEditView;
import Goliath.UI.Views.TableSelectView;
import Goliath.UI.Views.View;
import Goliath.UI.Windows.ApplicationPanelCommand;
import Goliath.UI.Windows.ApplicationPanelType;
import Goliath.UI.Windows.Window;


        
/**
 * Class Description.
 * For example:
 * <pre>
 *      Example usage
 * </pre>
 *
 * @see         Related Class
 * @version     1.0 Dec 24, 2010
 * @author      kenmchugh
**/
public class BusinessObjectEditPanelCommand extends ApplicationPanelCommand
{
    private static HashTable<Class, TableSelectView> g_oTableEditViews;

    public BusinessObjectEditPanelCommand()
    {
        setIndex(2);
    }

    private static <T extends BusinessObject> TableSelectView<T> getTableEditView(Class<T> toClass)
    {
        if (g_oTableEditViews == null)
        {
            g_oTableEditViews = new HashTable<Class, TableSelectView>();
        }
        if (!g_oTableEditViews.containsKey(toClass))
        {
            TableSelectView loView;
            if (Java.isEqualOrAssignable(DynamicDataObject.class, toClass))
            {
                loView = new DynamicDataObjectEditView(toClass);
            }
            else
            {
                loView = new BusinessObjectEditView(toClass);
            }

            
            Window loWindow = new Window();
            loWindow.setTitle("Business Object List for " + toClass.getName());
            loWindow.addControl(loView);

            g_oTableEditViews.put(toClass, loView);
        }
        return g_oTableEditViews.get(toClass);
    }


    @Override
    public String getCommandName()
    {
        return "Business Object Edit";
    }

    @Override
    protected String getCommandImageSource()
    {
        return "./resources/Images/Icons/Settings.png";
    }

    @Override
    public String getCommandTooltip()
    {
        return "View and edit your Business Objects";
    }

    @Override
    public ApplicationPanelType getPanelType()
    {
        return ApplicationPanelType.TOOLS();
    }

    protected boolean accepts(Class<BusinessObject> toClass)
    {
        return toClass.getSuperclass().equals(BusinessObject.class);
    }

    @Override
    public View createCommandView()
    {
        Skin loSkin = SkinManager.getInstance().getCurrentSkin();
        View loView = new View();

        // Get a list of all the Explorer Panel Commands that are available
        List<Class<BusinessObject>> loCommandClasses = Application.getInstance().getObjectCache().getClasses(BusinessObject.class);
        // Go through each command and create the panels
        for (Class<BusinessObject> loCommandClass : loCommandClasses)
        {
            // Only use the classes that are accepted
            if (!accepts(loCommandClass))
            {
                continue;
            }
            try
            {
                // Get the panel the command is supposed to be in and make sure it exists in the Accordion

                // Create the button for the command
                Button loButton = createCommandButton(loCommandClass);
                if (loButton != null)
                {
                    loView.addControl(loButton);
                }
            }
            catch (Throwable ex)
            {
                Application.getInstance().log(ex);
            }
        }

        //loView.setLayoutManager(new GridLayoutManager(3, (int)Math.ceil((double)loView.getChildren().size()/3.0)));
        loView.setLayoutManager(GridLayoutManager.class);
        return loView;
    }

    /**
     * Hook to allow sub class to add any event listeners required
     * @param toWindow the window to add the listeners too.
     */
    @Override
    protected void addEventListeners(IWindow toWindow)
    {
        toWindow.addEventListener(UIEventType.ONDEACTIVATED(), Delegate.build(this, "onPanelDeactivated"));
    }

    /**
     * When the configuration panel is deactivated, it will close automatically
     * @param toEvent the event that occured
     */
    private void onPanelDeactivated(Event<IControl> toEvent)
    {
        Window loWindow = (Window)toEvent.getTarget();
        if (loWindow != null)
        {
            loWindow.close();
        }
    }

    @Override
    protected void configureWindow(Event<IControl> toEvent, IWindow toWindow)
    {
        toWindow.setShowChrome(false);
        IWindow loTargetWindow = toEvent.getTarget().getParentWindow();
        toWindow.setLocation(loTargetWindow.getSize().getWidth() + loTargetWindow.getLocation().getX(), toEvent.getTarget().getScreenCoordinates().getY());
    }

    /**
     * Creates the Buttons for each Explorer command
     * @param toCommand the command to create a button for
     * @return the Button
     */
    private <T extends BusinessObject> Button createCommandButton(Class<T> toDataObject)
    {
        Button loReturn = new Button(Delegate.build(this, "onDataObjectClicked"));
        loReturn.setProperty("dataObjectClass", toDataObject);
        loReturn.setText(toDataObject.getSimpleName());
        loReturn.setTooltip(toDataObject.getName());
        return loReturn;
    }

    private void onDataObjectClicked(Event<IButton> toEvent)
    {
        try
        {
            TableSelectView loView = getTableEditView((Class)toEvent.getTarget().getProperty("dataObjectClass"));
            loView.getParentWindow().setSize(800, 800);
            loView.getParentWindow().setLocation(getApplicationWindow().getLocation().getX() + getApplicationWindow().getSize().getWidth(), getApplicationWindow().getLocation().getY());
            loView.getParentWindow().setVisible(!loView.getParentWindow().isVisible());
        }
        catch(Throwable ex)
        {
            Application.getInstance().log(ex);
        }
    }
}