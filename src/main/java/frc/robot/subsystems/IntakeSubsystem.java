package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
    private final SparkMax hinge = new SparkMax(Constants.IntakeConstants.hingeID, SparkLowLevel.MotorType.kBrushless);
    private final SparkMax roller =  new SparkMax(Constants.IntakeConstants.rollerID, SparkLowLevel.MotorType.kBrushless);


    public void hingeDown() {
        hinge.setVoltage(-Constants.IntakeConstants.hingeVoltageReverse);
    }

    public void hingeUp() {
        hinge.setVoltage(Constants.IntakeConstants.hingeVoltage);
    }

    public void hingeStop() {
        hinge.setVoltage(0);
    }

    public void rollerDown() {
        roller.setVoltage(-Constants.IntakeConstants.rollerVoltageReverse);
    }

    public void rollerUp() {
        roller.setVoltage(Constants.IntakeConstants.rollerVoltage);
    }

    public void rollerStop() {
        roller.setVoltage(0);
    }
}

