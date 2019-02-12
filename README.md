# AlienAttack
Team 12s awesome new shoot'em up game <br/>

This current version of JoshBranch (12/02/19) works on windows and has shooting semi implemented.<br/>
Author: Josh Leeder<br/>
<br/>
The shooting is based around an abstract bullet class, with concrete bullet types (e.g. HandGun) extended it.<br/>
Currently:<br/>
A new bullet is created on mouse click and is added to the singleton BulletList<br/>
The bullets in this list render and travel in a positive x direction from the character (and are also updated via the same list)<br/>
However, there is no dispose method yet, to remove the bullets we use a boolean in the bullet class (toRemove) which will be set to<br/>
true upon collision with something (entity or level boundary)<br/>
When iterating through the bullet list if we detect a bullet has toRemove as true the bullet is added to a remove list<br/>
This remove list is then iterated over to remove bullets fromt the bulletList (then it is cleared itself)<br/>
This is done to stop concurrency errors (which occur if tryingto remove bullets from the builletList whilse iterating over it)<br/>
<br/>
To do:<br/>
-Slow down the travel of bullets<br/>
-Implement collision detection on the bullets<br/>
-Change the graphics for the bullets (currently a simple square)<br/>
-Remove bullets from the bullet list (once they collide with something, as the rendering ddraws from the bullet list the graphics should<br/>
disappear after the bullet is removed from the list)<br/>
-Make bullets travel in the direction of mouse click (Still thinking of the best way to do this, do i need to set an end point for bullets<br/>
off of the screen so they always travel towards it, instead of hitting a spot and freezing)<br/>
-Differnt types of bullets and their behaviours (Shotgun Machine gun etc)<br/>
-Weapon classes to determine the bullet behaviour for the player<br/>
