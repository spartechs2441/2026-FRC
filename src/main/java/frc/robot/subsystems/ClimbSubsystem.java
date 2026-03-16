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

    public boolean getLimitSwitch() {
        return false;
    }
    public void winchUp() {
        if (climbEncoder.getPosition() < Constants.ClimbConstants.RelativeEncoder) {
            winch.setVoltage(Constants.ClimbConstants.leftWinchVoltage);
        } else {
            winchStop();
        }
    }

    public void winchDown() {
        System.out.println("Limit switch " + digitalSwitch.get());
        winch.setVoltage(-Constants.ClimbConstants.leftWinchVoltage);
    }

    public void winchStop() {
        winch.setVoltage(0);

    }
}

