package com.system.shailendra.core.repositories

import androidx.room.Database
import androidx.room.DeleteTable
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.system.shailendra.core.entities.FuelHeavyVehicleEntity
import com.system.shailendra.core.entities.FuelTruckEntity
import com.system.shailendra.core.entities.MaterialEntity
import com.system.shailendra.core.repositories.fuel.heavy_vehicle.HeavyVehicleDao
import com.system.shailendra.core.repositories.fuel.truck.TruckFuelDao
import com.system.shailendra.core.repositories.material.MaterialDao

@Database(
    entities = [MaterialEntity::class, FuelTruckEntity::class, FuelHeavyVehicleEntity::class],
    version = 6
)
abstract class AppDatabase: RoomDatabase() {
    abstract val materialDao: MaterialDao
    abstract val truckFuelDao: TruckFuelDao
    abstract val heavyVehicleDao: HeavyVehicleDao
}

@DeleteTable.Entries(
    DeleteTable(tableName = "gas")
)
class Migration23: AutoMigrationSpec