package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {
    private final SparkMax winch = new SparkMax(12, SparkLowLevel.MotorType.kBrushless);
    private final RelativeEncoder climbEncoder = winch.getEncoder();
    private final DigitalInput digitalSwitch = new DigitalInput(0);

    /// Returns true if the limit switch is pressed down
    public boolean limitSwitchDown() {
        // true means that it isn't being pressed down
        // false means that it is pressed down
        // so we are reversing it
        return !digitalSwitch.get();
    }
    public void winchUp() {
        if (climbEncoder.getPosition() < Constants.ClimbConstants.RelativeEncoder) {
            winch.setVoltage(Constants.ClimbConstants.leftWinchVoltage);
        } else {
            winchStop();
        }
    }

    public void winchDown() {
        if (limitSwitchDown()) {
            winchStop();
        } else {
            winch.setVoltage(-Constants.ClimbConstants.leftWinchVoltage);
        }
    }

    public void winchStop() {
        winch.setVoltage(0);

    }
}

