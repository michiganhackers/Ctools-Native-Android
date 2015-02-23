CTools Native Android
===========================
A cross platform native app which interfaces with CTools for iOS and Android.

MVP Features
-----
- Announcements
- Content/Resources
- Gradebook
- Calendar*

*Calendar Google ID available through API, but unable to access actual calendar. May be possible through Google APIs

Details
-----
- Push notifications when given new assignments, assuming the authentication cookie allows us to check for updates in the background/when app is closed.
- Using Shibboleth authentication through UMich (see @davidquesada Github for iOS [cookie example](https://github.com/davidquesada/BluePrinter/blob/863ecfe42adef21063cde584fd2ed245a9461567/BluePrinterCore/BluePrinterCore/Model/Network/MPrintCosignManager.m))
- Announcements, Resources, Gradebook are all directly and reliably accessible through the CTools direct API
- Calendar* and Videos can be gotten from scraping the websites, 'gcalid' can be gotten through API but need to access the actual calendar through Google APIs
- Assignments only available if no assignments are posted, otherwise throws a 403 (Forbidden) error.

API endpoints & Usage
-----
`https://ctools.umich.edu/site.{JSON/XML}`
- for complete list of available classes, their ids, etc.

`https://ctools.umich.edu/direct/{ENDPOINT}/site/{SITE ID}.{JSON/XML}`
- Announcements `announcement`
- Content/Resources `content`
- Gradebook `gradebook`
- Calendar (can get google id but no way to access calendar) *obtain from full class list*
- Assignments? (lots of 403) (from perspective of teacher) `assignment`
- Videos (406. page exists but format errors?) `kaltura`
- Forum `forums`
- iClicker registration `iclicker`
- Dropbox `dropbox`

[License](LICENSE.md)
-----
