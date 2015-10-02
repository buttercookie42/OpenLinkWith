package com.tasomaniac.openwith.data;

import android.net.Uri;

import com.tasomaniac.openwith.BuildConfig;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = OpenWithProvider.AUTHORITY,
        database = OpenWithDatabase.class,
        packageName = "com.tasomaniac.openwith.provider")
public final class OpenWithProvider {

    private OpenWithProvider() {
    }

    public static final String AUTHORITY = BuildConfig.APPLICATION_ID;

    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    @TableEndpoint(table = OpenWithDatabase.OPENWITH)
    public static class OpenWithHosts {

        @ContentUri(
                path = OpenWithDatabase.OPENWITH,
                type = "vnd.android.cursor.dir/openwith")
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,
                OpenWithDatabase.OPENWITH);

        @InexactContentUri(
                name = "OPENWITH_ID",
                path = OpenWithDatabase.OPENWITH + "/#",
                type = "vnd.android.cursor.item/openwith",
                whereColumn = OpenWithDatabase.OpenWithColumns.ID,
                pathSegment = 1)
        public static Uri withId(long id) {
            return Uri.withAppendedPath(CONTENT_URI, String.valueOf(id));
        }

        @InexactContentUri(
                name = "OPENWITH_HOST",
                path = OpenWithDatabase.OPENWITH + "/host/" + "*",
                type = "vnd.android.cursor.item/openwith",
                whereColumn = OpenWithDatabase.OpenWithColumns.HOST,
                pathSegment = 2)
        public static Uri withHost(String host) {
            return CONTENT_URI.buildUpon()
                    .appendEncodedPath("host")
                    .appendPath(host).build();
        }
    }
}