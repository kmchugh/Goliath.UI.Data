/* ========================================================
 * BusinessObjectEditView.java
 *
 * Author:      kenmchugh
 * Created:     Dec 24, 2010, 4:40:52 PM
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

import Goliath.Collections.BusinessObjectCollection;
import Goliath.Data.BusinessObjects.BusinessObject;
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
 * @version     1.0 Dec 24, 2010
 * @author      kenmchugh
**/
public class BusinessObjectEditView<T extends BusinessObject> extends TableSelectView<T>
{
    /**
     * Creates a new instance of BusinessObjectEditView
     */
    public BusinessObjectEditView(Class<T> toClass)
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
    protected boolean onSave(T toItem)
    {
        if (!toItem.save())
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
        BusinessObjectCollection<T> loCollection = new BusinessObjectCollection<T>(getSupportedClass());
        loCollection.loadList();
        return loCollection;
    }

}
