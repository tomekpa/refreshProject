------------------------------------------------------------------------------------
-- Ultimate 2016.3 no templates fix
------------------------------------------------------------------------------------

Solved by using 32 bit launcher instead of 64 bit !
OR
Add -Djdk.util.zip.ensureTrailingSlash=false in Help | Edit Custom VM Options.
There was incompatible change in the recent Java update which breaks some features in the old IntelliJ IDEA versions.
