/* ========================================================
 * DataObjectEditView.java
 *
 * Author:      kmchugh
 * Created:     Dec 8, 2010, 8:07:25 PM
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

import Goliath.Collections.SimpleDataObjectCollection;
import Goliath.Data.DataObjects.DataObject;
import Goliath.Interfaces.Collections.IList;
import Goliath.Validation.BrokenRule;


        
/**
 * Class Description.
 * For example:
 * <pre>
 *      Example usage
 * </pre>
 *
 * @see         Related Class
 * @version     1.0 Dec 8, 2010
 * @author      kmchugh
**/
public class DataObjectSelectView<T extends DataObject<T>> extends
        SimpleDataObjectSelectView<T>
{
    /**
     * Creates a new instance of DataObjectEditView
     */
    public DataObjectSelectView(Class<T> toClass)
    {
        super(toClass);
    }


    @Override
    protected boolean onSave(T toItem)
    {
        boolean llReturn = super.onSave(toItem);
        if (!llReturn)
        {
            StringBuilder loBuilder = new StringBuilder();
            for (BrokenRule loRule : toItem.getBrokenRules())
            {
                loBuilder.append(loRule.getMessage());
            }
            
            // TODO: Highlight the invalid properties and their names

            return false;
        }
        return true;
    }

    @Override
    protected IList<T> loadData()
    {
        SimpleDataObjectCollection<T> loCollection = new SimpleDataObjectCollection<T>(getSupportedClass());
        loCollection.loadList();
        return loCollection;
    }
}
