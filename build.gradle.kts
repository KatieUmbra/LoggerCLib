tasks.register("publishAll") {
    group = "Global Tasks"
    dependsOn("Core:publish")
    dependsOn("Color:publish")
    dependsOn("File:publish")
}

tasks.register("testAll") {
    group = "Global Tasks"
    dependsOn("Core:test")
    dependsOn("Color:test")
    dependsOn("File:test")
}