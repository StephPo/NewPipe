package org.schabi.newpipe.local.subscription.dialog;

import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import org.schabi.newpipe.R;
import org.schabi.newpipe.database.feed.model.FeedGroupEntity;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class SelectChannelGroupDialog {
    private final AlertDialog dialog;

    public SelectChannelGroupDialog(@NonNull final Context context,
                                    @NonNull final ChooseChannelGroupListItem[] items,
                                    @NonNull final DialogInterface.OnClickListener actions) {
        final CharSequence[] itemsCharSequence = Arrays.stream(items).map(i -> i.groupName).collect(Collectors.toList()).toArray(new String[0]);
        dialog = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.feed_group_select_title))
                .setItems(itemsCharSequence, actions)
                .create();
    }

    public void show() {
        dialog.show();
    }

    public static final class ChooseChannelGroupListItem {
        final long groupId;
        final String groupName;

        public ChooseChannelGroupListItem(final FeedGroupEntity feedGroupEntity) {
            this(feedGroupEntity.getUid(), feedGroupEntity.getName());
        }

        ChooseChannelGroupListItem(final long groupId, final String groupName) {
            this.groupId = groupId;
            this.groupName = groupName;
        }

        String getGroupName() {
            return groupName;
        }

        public long getGroupId() {
            return groupId;
        }
    }
}
