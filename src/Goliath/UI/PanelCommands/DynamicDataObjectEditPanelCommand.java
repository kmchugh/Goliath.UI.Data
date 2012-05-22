/* ========================================================
 * DynamicDataObjectEditPanelCommand.java
 *
 * Author:      kenmchugh
 * Created:     Dec 30, 2010, 10:47:00 AM
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

import Goliath.Data.BusinessObjects.BusinessObject;
import Goliath.Data.BusinessObjects.DynamicDataObject;
import Goliath.DynamicCode.Java;


        
/**
 * Class Description.
 * For example:
 * <pre>
 *      Example usage
 * </pre>
 *
 * @see         Related Class
 * @version     1.0 Dec 30, 2010
 * @author      kenmchugh
**/
public class DynamicDataObjectEditPanelCommand extends BusinessObjectEditPanelCommand
{
    /**
     * Creates a new instance of DynamicDataObjectEditPanelCommand
     */
    public DynamicDataObjectEditPanelCommand()
    {
    }

    @Override
    public String getCommandName()
    {
        return "Dynamic Data Object Edit";
    }

    @Override
    protected String getCommandImageSource()
    {
        return "./resources/Images/Icons/Settings.png";
    }

    @Override
    public String getCommandTooltip()
    {
        return "View and edit your Dynamic Data Objects";
    }

    @Override
    protected boolean accepts(Class<BusinessObject> toClass)
    {
        return Java.isEqualOrAssignable(DynamicDataObject.class, toClass);
    }
}
