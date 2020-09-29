# InstagramSpring
Service which provides data from Instagram.

## Main endpoints

### /api/add/{profileName}/{category}
Adds video clips from selected profile to database.

---

### /api/profile/{name}
Returns first page of medias.
#### userId, end_cursor, avatarUrl, channelName, photosList, videosList

---

### /api/profile/{userId}/{end_cursor}
Return current page of medias.
#### userId, end_cursor, photosList, videosList

---

### /api/profile/{profileName}/all
Return every page of medias. Next page is called after 3seconds to prevent Instagram block.
#### userId, end_cursor, avatarUrl, channelName, photosList, videosList

---


end_cursor is variable required for calling next page.
