package minxie.space.metrics.enums

import minxie.space.metrics.vo.DisplayEnum

/**
 * 指标key枚举
 */
enum class MetricKeyEnum(val key: String, val display: DisplayEnum) {
    // 总览
    LOADED_CLASSES("jvm_classes_currently_loaded", DisplayEnum.OVERVIEW),
    TOTAL_LOADED_CLASSES("jvm_classes_loaded_total", DisplayEnum.OVERVIEW),
    UNLOADED_CLASSES("jvm_classes_unloaded_total", DisplayEnum.OVERVIEW),
    JAVA_LANG_OPERATING_SYSTEM_TOTAL_PHYSICAL_MEMORY_SIZE(
        "java_lang_OperatingSystem_TotalPhysicalMemorySize",
        DisplayEnum.OVERVIEW
    ),
    JAVA_LANG_OPERATING_SYSTEM_COMMITTED_VIRTUAL_MEMORY_SIZE(
        "java_lang_OperatingSystem_CommittedVirtualMemorySize",
        DisplayEnum.OVERVIEW
    ),
    JAVA_LANG_OPERATING_SYSTEM_FREE_MEMORY_SIZE("java_lang_OperatingSystem_FreeMemorySize", DisplayEnum.OVERVIEW),
    PROCESS_START_TIME_SECONDS("process_start_time_seconds", DisplayEnum.OVERVIEW),
    JAVA_LANG_OPERATING_SYSTEM_AVAILABLE_PROCESSORS(
        "java_lang_OperatingSystem_AvailableProcessors",
        DisplayEnum.OVERVIEW
    ),
    JAVA_LANG_OPERATING_SYSTEM_SYSTEM_LOAD_AVERAGE("java_lang_OperatingSystem_SystemLoadAverage", DisplayEnum.OVERVIEW),
    JAVA_LANG_OPERATING_SYSTEM_PROCESS_CPU_LOAD("java_lang_OperatingSystem_ProcessCpuLoad", DisplayEnum.OVERVIEW),
    JAVA_LANG_OPERATING_SYSTEM_CPU_LOAD("java_lang_OperatingSystem_CpuLoad", DisplayEnum.OVERVIEW),
    PROCESS_OPEN_FDS("process_open_fds", DisplayEnum.OVERVIEW),
    PROCESS_MAX_FDS("process_max_fds", DisplayEnum.OVERVIEW),
    JVM_INFO("jvm_info", DisplayEnum.OVERVIEW),

    // 垃圾回收
    JVM_GC_COLLECTION_SECONDS_COUNT("jvm_gc_collection_seconds_count", DisplayEnum.GC),
    JVM_GC_COLLECTION_SECONDS_SUM("jvm_gc_collection_seconds_sum", DisplayEnum.GC),

    // 线程状态
    JVM_THREADS_CURRENT("jvm_threads_current", DisplayEnum.THREAD),
    JVM_THREADS_DAEMON("jvm_threads_daemon", DisplayEnum.THREAD),
    JVM_THREADS_PEAK("jvm_threads_peak", DisplayEnum.THREAD),
    JVM_THREADS_STARTED_TOTAL("jvm_threads_started_total", DisplayEnum.THREAD),
    JVM_THREADS_DEADLOCKED("jvm_threads_deadlocked", DisplayEnum.THREAD),
    JVM_THREADS_DEADLOCKED_MONITOR("jvm_threads_deadlocked_monitor", DisplayEnum.THREAD),
    JVM_THREADS_STATE("jvm_threads_state", DisplayEnum.THREAD),

    // 单个线程
    JVM_THREADS("jvm_threads", DisplayEnum.THREAD_INFO),

    // 堆内存
    JVM_MEMORY_BYTES_USED("jvm_memory_bytes_used", DisplayEnum.HEAP_MEMORY),
    JVM_MEMORY_BYTES_MAX("jvm_memory_bytes_max", DisplayEnum.HEAP_MEMORY),
    JVM_MEMORY_POOL_BYTES_MAX("jvm_memory_pool_bytes_max", DisplayEnum.HEAP_MEMORY),
    JVM_MEMORY_POOL_BYTES_USED("jvm_memory_pool_bytes_used", DisplayEnum.HEAP_MEMORY),
    JVM_MEMORY_POOL_BYTES_COMMITTED("jvm_memory_pool_bytes_committed", DisplayEnum.HEAP_MEMORY),

    // JDK线程池
    JDK_THREAD_POOL_ACTIVE_COUNT("jdk_thread_pool_active_count", DisplayEnum.JDK_THREAD_POOL),
    JDK_THREAD_POOL_CORE_POOL_SIZE("jdk_thread_pool_core_pool_size", DisplayEnum.JDK_THREAD_POOL),
    JDK_THREAD_POOL_MAXIMUM_POOL_SIZE("jdk_thread_pool_maximum_pool_size", DisplayEnum.JDK_THREAD_POOL),
    JDK_THREAD_POOL_QUEUE_SIZE("jdk_thread_pool_queue_size", DisplayEnum.JDK_THREAD_POOL),
    JDK_THREAD_POOL_COMPLETED_TASK_COUNT("jdk_thread_pool_completed_task_count", DisplayEnum.JDK_THREAD_POOL),

    // Dubbo线程池
    DUBBO_THREAD_POOL_ACTIVE_COUNT("dubbo_thread_pool_active_count", DisplayEnum.DUBBO_THREAD_POOL),
    DUBBO_THREAD_POOL_CORE_POOL_SIZE("dubbo_thread_pool_core_pool_size", DisplayEnum.DUBBO_THREAD_POOL),
    DUBBO_THREAD_POOL_MAXIMUM_POOL_SIZE("dubbo_thread_pool_maximum_pool_size", DisplayEnum.DUBBO_THREAD_POOL),
    DUBBO_THREAD_POOL_QUEUE_SIZE("dubbo_thread_pool_queue_size", DisplayEnum.DUBBO_THREAD_POOL),
    DUBBO_THREAD_POOL_COMPLETED_TASK_COUNT("dubbo_thread_pool_completed_task_count", DisplayEnum.DUBBO_THREAD_POOL),

    // RocketMQ线程池
    ROCKETMQ_THREAD_POOL_ACTIVE_COUNT("rocketmq_thread_pool_active_count", DisplayEnum.ROCKETMQ_THREAD_POOL),
    ROCKETMQ_THREAD_POOL_CORE_POOL_SIZE("rocketmq_thread_pool_core_pool_size", DisplayEnum.ROCKETMQ_THREAD_POOL),
    ROCKETMQ_THREAD_POOL_MAXIMUM_POOL_SIZE("rocketmq_thread_pool_maximum_pool_size", DisplayEnum.ROCKETMQ_THREAD_POOL),
    ROCKETMQ_THREAD_POOL_QUEUE_SIZE("rocketmq_thread_pool_queue_size", DisplayEnum.ROCKETMQ_THREAD_POOL),
    ROCKETMQ_THREAD_POOL_COMPLETED_TASK_COUNT(
        "rocketmq_thread_pool_completed_task_count",
        DisplayEnum.ROCKETMQ_THREAD_POOL
    ),

    // Tomcat线程池
    TOMCAT_THREAD_POOL_ACTIVE_COUNT("tomcat_thread_pool_active_count", DisplayEnum.TOMCAT_THREAD_POOL),
    TOMCAT_THREAD_POOL_CORE_POOL_SIZE("tomcat_thread_pool_core_pool_size", DisplayEnum.TOMCAT_THREAD_POOL),
    TOMCAT_THREAD_POOL_MAXIMUM_POOL_SIZE("tomcat_thread_pool_maximum_pool_size", DisplayEnum.TOMCAT_THREAD_POOL),
    TOMCAT_THREAD_POOL_QUEUE_SIZE("tomcat_thread_pool_queue_size", DisplayEnum.TOMCAT_THREAD_POOL),
    TOMCAT_THREAD_POOL_COMPLETED_TASK_COUNT("tomcat_thread_pool_completed_task_count", DisplayEnum.TOMCAT_THREAD_POOL)
}