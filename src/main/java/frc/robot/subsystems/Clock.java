package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.*;

public class Clock extends Subsystem {

  public TalonSRX clock = new TalonSRX(RobotMap.clockPort);
  
  public static enum ClockPreset{
    TWELVE(0),
    THREE(20),
    SIX(40),
    NINE(60);

    public double clockPosition;
    private ClockPreset(double clockPosition) {
        this.clockPosition = clockPosition;
      }
    }

  ///////////////////////////////
  public void configMotorController(int timeout){
    clock.config_kP(0, 4, timeout);
    clock.config_kI(0, 0, timeout);
    clock.config_kD(0, 55, timeout);
    clock.config_kF(0, 4.55, timeout);
    clock.config_IntegralZone(0, 0, timeout);
  
    clock.configMotionAcceleration(300, timeout); //was 400
    clock.configMotionCruiseVelocity(200, timeout); //was 400
    clock.setNeutralMode(NeutralMode.Brake);
  
    clock.setSensorPhase(true);
    clock.setInverted(true);
  
    clock.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, timeout);
  }  

  ///////////////////////////////
  public Clock (){
    configMotorController(10);
  }

  ///////////////////////////////
  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new SetClock(OI.clock));
    // Set the default command for a subsystem here.    
  }

  ///////////////////////////////
  public void periodic(){
    SmartDashboard.putNumber("Clock pos", getCurrentPosition());
  }

  ///////////////////////////////
  public int getCurrentPosition() {
    return clock.getSelectedSensorPosition(0);
  }

  ///////////////////////////////
  public void setPosition(ClockPreset position){
    clock.set(ControlMode.MotionMagic, position.clockPosition);
  }

  ///////////////////////////////
  public void setSpeed(double speed){
    clock.set(ControlMode.PercentOutput, speed);
  }

  ///////////////////////////////
  void setOutput(double output) {
    clock.set(ControlMode.PercentOutput, output);
  }

  ///////////////////////////////
  public void stop() {
    clock.set(ControlMode.PercentOutput, 0);
  }

}