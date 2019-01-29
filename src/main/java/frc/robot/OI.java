/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.command.ResetEncoders;
import frc.robot.command.driveForward;

public class OI {

    public Joystick driveJoyLeft = new Joystick(1);
    public Joystick driveJoyRight = new Joystick(0);

    public OI(){
        SmartDashboard.putData("drive Dis", new driveForward(5000));
        SmartDashboard.putData("asuodahdsu", new ResetEncoders());
    }
    // public double getJoyLeftY(){
    //     return driveJoyLeft.getY();
    // }

    // public double getJoyRightY(){
    //     return driveJoyRight.getY();
    // }
}
