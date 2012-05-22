/* ========================================================
 * SimpleDataObjectSelectView.java
 *
 * Author:      admin
 * Created:     Jul 21, 2011, 10:13:52 AM
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
package Goliath.UI.Views;

import Goliath.Applications.Application;
import Goliath.Collections.List;
import Goliath.Collections.SimpleDataObjectCollection;
import Goliath.Data.DataObjects.SimpleDataObject;
import Goliath.Interfaces.Collections.IList;
import Goliath.UI.Controls.UserControls.DynamicEditors.ObjectEditor;

/**
 * Class Description.
 * For example:
 * <pre>
 *      Example usage
 * </pre>
 *
 * @see         Related Class
 * @version     1.0 Jul 21, 2011
 * @author      admin
 **/
public class SimpleDataObjectSelectView<T extends SimpleDataObject<T>> extends TableSelectView<T>
{
    /**
     * Creates a new instance of BusinessObjectEditView
     */
    public SimpleDataObjectSelectView(Class<T> toClass)
    {
        super(toClass, true, true, true);
    }

    @Override
    protected boolean onDelete(IList<T> toItems)
    {
        boolean llReturn = true;
        for (T loItem : toItems)
        {
            loItem.delete();
            llReturn = llReturn && loItem.save();
        }
        return llReturn;
    }

    @Override
    protected List<String> getObjectProperties()
    {
        List<String> loReturn = super.getObjectProperties();
        if (loReturn.contains("guid") || loReturn.contains("GUID"))
        {
            try
            {
                T loClassInstance = getSupportedClass().newInstance();
                if (!loClassInstance.hasGUID())
                {
                    loReturn.remove("guid");
                    loReturn.remove("GUID");
                }
            }
            catch (Throwable ex)
            {
                Application.getInstance().log(ex);
            }
        }
        
        // We need to remove the GUID if the data object does not have a guid
        return super.getObjectProperties();
    }

    @Override
    protected void configureObjectEditor(ObjectEditor toEditor)
    {
        // Hide the ID property from saving
        toEditor.setReadonlyProperty("ID");
    }
    
    
    
    

    @Override
    protected boolean onSave(T toItem)
    {
        return toItem.save();
    }

    @Override
    protected IList<T> loadData()
    {
        SimpleDataObjectCollection<T> loCollection = new SimpleDataObjectCollection<T>(getSupportedClass());
        loCollection.loadList();
        return loCollection;
    }

}