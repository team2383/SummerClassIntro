package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.HAL;
import frc.robot.subsystems.Clock.ClockPreset;
import frc.robot.subsystems.Clock;

public class SetClock extends Command {

    private ClockPreset clockPreset;

    DoubleSupplier clockManual;
    boolean manual;

    public SetClock(ClockPreset clockPreset){
        requires(HAL.clock);
        manual = false;
        this.clockPreset = clockPreset;
    }

    public SetClock(DoubleSupplier clockManual) {
      this.clockManual = clockManual;
      manual = true;
      requires(HAL.clock);
    }

    @Override
    protected void initialize() {
    }
  
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
      // if (clockManual.getAsDouble() > 0.1 && manual == true){
      //  HAL.clock.setSpeed(clockManual.getAsDouble());
      // }
      // else if (clockManualBack.getAsDouble() > 0.1 && manual == true){
      //   HAL.clock.setSpeed(clockManualBack.getAsDouble() * -1);
      // }
      // else{
      //   HAL.clock.setPosition(clockPreset);
      // }

      if (manual){
        HAL.clock.setSpeed(clockManual.getAsDouble());
      }
      else{
      HAL.clock.setPosition(clockPreset);
      }
    }
  
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
      // int clockCurPos = HAL.clock.getCurrentPosition();
      // double clockDesiredPos = HAL.clock.getDesiredPosition();
      
      // if ((Math.abs(clockCurPos - clockDesiredPos) < 50)){
      //   return true;
      // }
      // else {
      //   return false;
      // }
      return false;
    }
  
    // Called once after isFinished returns true
    @Override
    protected void end() {
      //manual = true;
    }
  
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
      //manual = true;
    }
}