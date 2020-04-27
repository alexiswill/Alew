package ale;

import robocode.HitRobotEvent;

import robocode.Robot;

import robocode.ScannedRobotEvent;
import robocode.WinEvent;

import java.awt.*;



public class Alew extends Robot {

	

	boolean peek; //Don't turn if there's a robot there 

	double moveAmount; //how much to move

	

	public void run() {

		    setBodyColor(Color.magenta);

			setGunColor(Color.magenta);

			setRadarColor(Color.magenta);

			setBulletColor(Color.cyan);

			setScanColor(Color.cyan);
			
			
			//Initialize moveAmount the max amount of movement
			moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());

			peek = false;
			
			
			while (true) {
				turnGunRight(10); // Scans automatically
			
				// turnLeft to face a wall.
				// getHeading() % 90 means the remainder of getHeading() divided by 90.
			turnLeft(getHeading() % 90);
			
			// Move up the wall
			ahead(moveAmount);

			
			// Turn the gun to turn right 90 degrees.
			peek = true;

			turnGunRight(90);

			// Turn to the next wall
			turnRight(90);

			

			while (true) {
				
				// Look before we turn when ahead() completes.
				peek = true;

				ahead(moveAmount);

				peek = false;

				turnRight(90);
		}	
	}
}		
	
	
	//onHit means move away 
	public void onHitRobot(HitRobotEvent e) {

		
		//back up if enemy is in front
		if (e.getBearing() > -90 && e.getBearing() < 90) {

			back(120);

		} 
		//move forward if enemy is in back
		else {

			ahead(150);

		}
}
			//fire
		public void onScannedRobot(ScannedRobotEvent e) {

			fire(5);
			
			// scan is called automatically when the robot is moving.
			// calling it manually makes another scan event if there's an enemy on the next wall so robot does not 
			//start moving up the wall until enemy gone.
		if (peek) {

			scan();
			
				// Calculate exact location of the robot
				double absoluteBearing = getHeading() + e.getBearing();
				double bearingFromGun = (absoluteBearing - getGunHeading());

				// If it's close enough, fire!
				if (Math.abs(bearingFromGun) <= 3) {
					turnGunRight(bearingFromGun);
					
					//check gun heat here. gun can not fire unless gun heat is set to 0.
					if (getGunHeat() == 0) {
						fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
					}
				} 
				// set the gun to turn.
				// no effect until scan() is called
				else {
					turnGunRight(bearingFromGun);
				}
				// Generates another scan event if enemy is in range
				// call only if the gun and radar are not turning
				//   Otherwise scan is called automatically.
				if (bearingFromGun == 0) {
					scan();
				}
		}
			}

			public void onWin(WinEvent e) {
			
				turnRight(36000);
			}
						


		

	}

		



	
		





