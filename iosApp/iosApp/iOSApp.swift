import SwiftUI
import Shared

@main
struct iOSApp: App {
    
    init() {
        KoinInitializerKt.doInitKoinIos()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
