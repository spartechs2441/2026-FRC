package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class StorageSubsystem extends SubsystemBase {
    private final SparkMax conveyor = new SparkMax(Constants.StorageConstants.conveyorID, SparkLowLevel.MotorType.kBrushless);

    public void conveyorForward() {
        conveyor.setVoltage(Constants.StorageConstants.conveyorVoltage);
    }

    public void conveyorBackward() {
        conveyor.setVoltage(-Constants.StorageConstants.conveyorVoltageReverse);
    }

    public void conveyorStop() {
        conveyor.setVoltage(0);
    }



}

