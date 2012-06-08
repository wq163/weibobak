@echo off
setLocal EnableDelayedExpansion
set CLASSPATH=.
for  %%a in (lib/*.jar) do (
   set CLASSPATH=!CLASSPATH!;lib/%%a
)
set CLASSPATH=!CLASSPATH!
rem echo %CLASSPATH%
call java -classpath %CLASSPATH% org.github.weibobak.Favorites.FavoriteBak
pause