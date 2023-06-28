tasks.register("publishAll") {
    dependsOn("Core:publish")
    dependsOn("Color:publish")
    dependsOn("File:publish")
}