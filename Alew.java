package ale;

import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.Rules;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class Alew extends Robot {
	
	boolean peek; 
	double moveAmount;
	
	public void run() {
		
			
			setBodyColor(Color.magenta);
			setGunColor(Color.magenta);
			setRadarColor(Color.magenta);
			setBulletColor(Color.cyan);
			setScanColor(Color.cyan);
			
			moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
			peek = false;
			
			turnLeft(getHeading() % 90);
			ahead(moveAmount);
			
			peek = true;
			turnGunRight(90);
			turnRight(90);
			
			while (true) {
				
				peek = true;
				ahead(moveAmount);
				peek = false;
				turnRight(90);
				
				
			}	
				
		
	}
	
			
		
	
	public void onHitRobot(HitRobotEvent e) {
		
		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(120);
		} 
		else {
			ahead(150);
		}
			
			
		}
		public void onScannedRobot(ScannedRobotEvent e) {
			fire(5);
		if (peek) {
			scan();
		}
	}
		
}




