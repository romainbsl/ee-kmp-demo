import UIKit
import shared

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    let commonBusiness: CommonBusiness

    override init() {
        setenv("CFNETWORK_DIAGNOSTICS", "3", 1);

        commonBusiness = CommonBusiness(ctx: PlatformContext())
        commonBusiness.userApi.fetchUsers(size: 100)
    }

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        return true
    }
    // MARK: UISceneSession Lifecycle

    func application(_ application: UIApplication, configurationForConnecting connectingSceneSession: UISceneSession, options: UIScene.ConnectionOptions) -> UISceneConfiguration {
        // Called when a new scene session is being created.
        // Use this method to select a configuration to create the new scene with.
        return UISceneConfiguration(name: "Default Configuration", sessionRole: connectingSceneSession.role)
    }

    func application(_ application: UIApplication, didDiscardSceneSessions sceneSessions: Set<UISceneSession>) {
        // Called when the user discards a scene session.
        // If any sessions were discarded while the application was not running, this will be called shortly after application:didFinishLaunchingWithOptions.
        // Use this method to release any resources that were specific to the discarded scenes, as they will not return.
    }


    static func get() -> AppDelegate { UIApplication.shared.delegate as! AppDelegate }
}

class UserList : ObservableObject {
    @Published var userList: [UserDomain]

    init() {
        userList = [UserDomain]()
    }
}
