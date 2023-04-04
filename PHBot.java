package com.PHBot;
import robocode.*;
import robocode.robotinterfaces.IBasicEvents;
import robocode.robotinterfaces.IBasicRobot;
import robocode.robotinterfaces.peer.IBasicRobotPeer;
import robocode.robotinterfaces.peer.IStandardRobotPeer;

import java.io.PrintStream;


/*
	* Robo gira a arma de 90 em 90 graus e efetua disparos ao encontrar um inimigo. Caso o meu robo seja atingido ele vai sair
	* da frente do outro robo e começar a fazer o scan novamente.
	*
*/
public class PHBot implements IBasicEvents, IBasicRobot, Runnable {

	PrintStream out;
	IStandardRobotPeer peer;
    private RobotStatus robotStatus;
	int countMiss = 0;
	boolean botFounded = false;
	double gunHeading = 0;

	public Runnable getRobotRunnable() {
		return this;
	}

	public IBasicEvents getBasicEventListener() {
		return this;
	}

	public void setPeer(IBasicRobotPeer iRobotPeer) {
		peer = (IStandardRobotPeer) iRobotPeer;
	}

	public void setOut(PrintStream printStream) {
		out = printStream;
	}
	
	public void turnGun(){
        if(!botFounded)
            peer.turnGun(Math.PI/2);
	}

	public void run() {
		while (true) {
				peer.move(100);
				turnGun();
				peer.move(-100);
				turnGun();
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
        // double angleToEnemy = e.getBearing();
		
		
        // double angle = Math.toRadians(robotStatus.getHeading() + angleToEnemy % 360);
        // botFounded = true;
        // peer.turnGun(angle);

        // double enemyX = (robotStatus.getX() + Math.sin(angle) * e.getDistance());
        // double enemyY = (robotStatus.getY() + Math.cos(angle) * e.getDistance());

        peer.setFire(10);
        
        // if(e.getHeadingRadians() < 200){
        //     botFounded = true;            
        // }
	}

	public void onHitByBullet(HitByBulletEvent e) {
		peer.turnBody(Math.PI / 2 + e.getBearingRadians());
	}

	public void onStatus(StatusEvent e) {
        this.robotStatus = e.getStatus();
    }

	public void onBulletHit(BulletHitEvent e) {
		
	}

	public void onBulletHitBullet(BulletHitBulletEvent e) {}

	public void onBulletMissed(BulletMissedEvent e) {
		// countMiss++;
		// if(countMiss > 6){
        //     botFounded = false;
		// 	peer.move(50);
		// 	countMiss=0;
		// }
	
	}

	public void onDeath(DeathEvent e) {}

	public void onHitRobot(HitRobotEvent e) {}

	public void onHitWall(HitWallEvent e) {}

	public void onRobotDeath(RobotDeathEvent e) {}

	public void onWin(WinEvent e) {}
}