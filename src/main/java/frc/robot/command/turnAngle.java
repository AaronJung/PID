/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.command;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.constants.constants;

public class turnAngle extends Command {

  private double m_prevError;
  private double m_error;
  private double m_output;
  private double m_desiredAngle;

  private double m_prevTime;
  private double m_time;

  private double m_dTerm;

  public turnAngle(double angle) {
    m_desiredAngle = angle;
    requires(Robot.m_drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    m_time = Timer.getFPGATimestamp();
    m_error = - m_desiredAngle - Robot.m_drive.getYaw();
    m_dTerm = (m_error - m_prevError) / (m_time - m_prevTime);

    m_output = constants.kP * m_error + constants.kD * m_dTerm;
    Robot.m_drive.setDrive(m_output, - m_output);

    m_prevTime = m_time;
    m_prevError = m_error;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(m_error) <= constants.tolerence;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drive.setDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
