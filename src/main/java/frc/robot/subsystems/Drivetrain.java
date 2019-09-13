package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand;

public class Drivetrain extends Subsystem {

  DifferentialDrive drive;

  public WPI_TalonSRX rightMaster = new WPI_TalonSRX(1);
  TalonSRX rightFollower = new TalonSRX(2);
  public WPI_TalonSRX leftMaster = new WPI_TalonSRX(3);
  TalonSRX leftFollower = new TalonSRX(4);

  public Drivetrain (){

    rightFollower.follow(rightMaster);
    leftFollower.follow(leftMaster);
    drive = new DifferentialDrive(leftMaster, rightMaster);

    leftMaster.setInverted(false);
    rightMaster.setInverted(false);
    leftFollower.setInverted(false);
    rightFollower.setInverted(false);
  }

  public void arcade(double move, double turn){
    drive.arcadeDrive(move, turn);
  }

  public void tank(double left, double right)
  {
    drive.tankDrive(left, right);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveCommand(OI.throttle,OI.turn));
  }
}