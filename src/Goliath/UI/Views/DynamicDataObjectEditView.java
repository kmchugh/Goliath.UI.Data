/* ========================================================
 * DynamicObjectEditView.java
 *
 * Author:      kmchugh
 * Created:     Dec 8, 2010, 8:09:11 PM
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

import Goliath.Collections.DynamicDataObjectCollection;
import Goliath.Data.BusinessObjects.DynamicDataObject;
import Goliath.Data.Query.DataQuery;
import Goliath.Interfaces.Collections.IList;

        
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
public class DynamicDataObjectEditView<T extends DynamicDataObject> extends BusinessObjectEditView<T>
{
    /**
     * Creates a new instance of DynamicObjectEditView
     */
    public DynamicDataObjectEditView(Class<T> toClass)
    {
        super(toClass);
    }

    @Override
    protected IList<T> loadData()
    {
        DynamicDataObjectCollection<T> loCollection = new DynamicDataObjectCollection<T>(getSupportedClass());
        loCollection.loadList((DataQuery)null);
        return loCollection;
    }
}
