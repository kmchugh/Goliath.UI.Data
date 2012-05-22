/* =======================================================*
 * LibraryVersion.java
 *
 * Author:      pStanbridge
 * Created:     Aug 14, 2011, 116:05:00 AM
 *
 * Description
 * --------------------------------------------------------
 * <Description>
 *
 * Change Log
 * --------------------------------------------------------
 * Init.Date        Ref.            Description
 * --------------------------------------------------------
 *
 * =======================================================*/

package Goliath.UI.Data;


/**
 *
 * @author kenmchugh
 */
public class LibraryVersion extends Goliath.Version
{
    public LibraryVersion(String tcName, int tnMajor, int tnMinor, int tnBuild, int tnRevision, String tcRelease, String tcDescription)
    {
        super(tcName, tnMajor, tnMinor, tnBuild, tnRevision, tcRelease, tcDescription);
    }

    public LibraryVersion()
    {
        // DO NOT MANUALLY MODIFY THIS FILE MAKE THE CHANGES IN BUILD.XML INSTEAD
        super(
                "Goliath.UI.Data",       //||--NAME
                1,        //||--MAJOR
                1,        //||--MINOR
                1,        //||--BUILD
                3474,     //||--REVISION
                "2012/01/16 13:39:45.837",      //||--RELEASE
                "Goliath Core Library"   //||--DESCRIPTION
                );
    }
}
