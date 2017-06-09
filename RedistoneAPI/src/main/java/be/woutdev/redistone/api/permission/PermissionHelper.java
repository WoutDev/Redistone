package be.woutdev.redistone.api.permission;

import be.woutdev.redistone.api.user.OfflineUser;

/**
 * Created by Wout on 8/06/2017.
 */
public interface PermissionHelper
{
    boolean hasPermission(OfflineUser user, String permission);

    boolean hasPermission(Group group, String permission);
}
