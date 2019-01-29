/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.command.TankDrive;
import edu.wpi.first.wpilibj.SPI;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class Drive extends Subsystem {
  public static TalonSRX m_leftSlave1;
  public static TalonSRX m_leftSlave2;
  public static TalonSRX m_leftMaster;

  public static TalonSRX m_rightSlave1;
  public static TalonSRX m_rightSlave2;
  public static TalonSRX m_rightMaster;
  
  public static AHRS m_gyro;

  public Drive(){
    m_leftSlave1 = new TalonSRX(3);
    m_leftSlave2 = new TalonSRX(2);
    m_leftMaster = new TalonSRX(1);

    m_rightSlave1 = new TalonSRX(6);
    m_rightSlave2 = new TalonSRX(5);
    m_rightMaster = new TalonSRX(4);

    // m_leftSlave1.follow(m_leftMaster);
    // m_leftSlave2.follow(m_leftMaster);

    // m_rightSlave1.follow(m_rightMaster);
    // m_rightSlave2.follow(m_rightMaster);

    m_gyro = new AHRS(SPI.Port.kMXP);
    }

    public double getYaw(){
      return m_gyro.getYaw();
    }

    public void setDrive(double leftPow, double rightPow){

      m_leftMaster.set(ControlMode.PercentOutput, leftPow);
      m_leftSlave1.set(ControlMode.PercentOutput, leftPow);
      m_leftSlave2.set(ControlMode.PercentOutput, leftPow);

      m_rightMaster.set(ControlMode.PercentOutput, rightPow);
      m_rightSlave1.set(ControlMode.PercentOutput, rightPow);
      m_rightSlave2.set(ControlMode.PercentOutput, rightPow);
    }

    public double getLeftPosition(){
      return m_leftMaster.getSelectedSensorPosition(0);
    }
    public double getRightPosition(){
      return m_rightMaster.getSelectedSensorPosition(0);
    }

    public double getAveragePosition(){
      return (getLeftPosition() + getRightPosition()) / 2;
    }

    public void reportToDash(){
      SmartDashboard.putNumber("right position", getRightPosition());
      SmartDashboard.putNumber("left position", getLeftPosition());
    }

    public void resetEnc(){
      m_rightMaster.setSelectedSensorPosition(0,0,0);
      m_rightSlave1.setSelectedSensorPosition(0,0,0);
      m_rightSlave2.setSelectedSensorPosition(0,0,0);
  
      m_leftMaster.setSelectedSensorPosition(0,0,0);
      m_leftSlave1.setSelectedSensorPosition(0,0,0);
      m_leftSlave2.setSelectedSensorPosition(0,0,0);
    }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new TankDrive());

  }
}
