package minxie.space.jmx.vo

import java.lang.management.ManagementFactory

class ClassMetricVo {
    // jvm_classes_currently_loaded
    private var loadedClasses = 0.0f

    // jvm_classes_loaded_total
    private var totalLoadedClasses = 0.0f

    // jvm_classes_unloaded_total
    private var unloadedClasses = 0.0f

    public constructor() {
        ManagementFactory.getClassLoadingMXBean().let {
            this.loadedClasses = it.loadedClassCount.toFloat()
            this.totalLoadedClasses = it.totalLoadedClassCount.toFloat()
            this.unloadedClasses = it.unloadedClassCount.toFloat()
        }
    }

    override fun toString(): String {
        return "jvm_classes_currently_loaded $loadedClasses ${System.lineSeparator()}" +
                "jvm_classes_loaded_total $totalLoadedClasses ${System.lineSeparator()}" +
                "jvm_classes_unloaded_total $unloadedClasses ${System.lineSeparator()}"
    }

}