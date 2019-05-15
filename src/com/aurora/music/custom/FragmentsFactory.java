/*
 * Copyright (c) 2019, Aurora OSS, Last Modified 2/7/19 5:04 PM
 * Copyright (C) 2007-2018, The Android Open Source Project
 * Copyright (c) 2014-2018, The Linux Foundation. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *    * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials provided
 *       with the distribution.
 *    * Neither the name of The Linux Foundation nor the names of its
 *       contributors may be used to endorse or promote products derived
 *       from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
 * IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.aurora.music.custom;

import android.app.Fragment;

import com.aurora.music.AlbumBrowserFragment;
import com.aurora.music.ArtistAlbumBrowserFragment;
import com.aurora.music.FolderBrowserFragment;
import com.aurora.music.MusicUtils;
import com.aurora.music.PlaylistBrowserFragment;
import com.aurora.music.TrackBrowserActivityFragment;
import com.aurora.music.TrackBrowserFragment;

import java.util.HashMap;

public class FragmentsFactory {

    private static HashMap<Integer, Fragment> frg = new HashMap<Integer, Fragment>();

    public static Fragment loadFragment(int pos) {

        if (frg.get(pos) == null) {
            frg.put(pos, createFrag(pos));
        }
        return frg.get(pos);
    }

    private static Fragment createFrag(int position) {
        FragmentEnum frg = FragmentEnum.values()[position];
        switch (frg) {
            case TRACK_FRAG:
                return new TrackBrowserFragment();
            case ALBUM_FRAG:
                return new AlbumBrowserFragment();
            case ARTIST_FRAG:
                return new ArtistAlbumBrowserFragment();
            case FOLDER_FRAG:
                if (MusicUtils.isGroupByFolder()) {
                    return new FolderBrowserFragment();
                }
                return new PlaylistBrowserFragment();
            case PLAYLIST_FRAG:
                return new PlaylistBrowserFragment();
            case TRACK_BROWSER:
                return new TrackBrowserActivityFragment();
        }
        return new ArtistAlbumBrowserFragment();
    }

    enum FragmentEnum {
        TRACK_FRAG, ALBUM_FRAG, ARTIST_FRAG, FOLDER_FRAG, PLAYLIST_FRAG, TRACK_BROWSER
    }
}
