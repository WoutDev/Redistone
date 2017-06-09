package be.woutdev.redistone.impl.permission;

import be.woutdev.redistone.api.permission.Group;
import be.woutdev.redistone.api.permission.Permission;
import be.woutdev.redistone.api.permission.PermissionHelper;
import be.woutdev.redistone.api.user.OfflineUser;

import java.util.stream.Stream;

/**
 * Created by Wout on 8/06/2017.
 */
public class UPermissionHelper implements PermissionHelper
{
    @Override
    public boolean hasPermission(OfflineUser user, String permission)
    {
        return hasPermission(user.getRank().getGroup(), permission);
    }

    @Override
    public boolean hasPermission(Group group, String permission)
    {
        Stream<Permission> stream = group.getPermissions().stream();

        return stream.noneMatch(p -> p.isNegative() && p.getNode().equalsIgnoreCase(permission)) &&
               (stream.anyMatch(p -> p.getNode().equalsIgnoreCase(permission)) || group.hasWildcardNode());

    }
}
