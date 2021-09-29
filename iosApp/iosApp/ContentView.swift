import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var userViewModel = UserViewModel(
        userRepository: AppDelegate.get().commonBusiness.userRepository
    )

    var body: some View {
        VStack {
            Text("User List (\(userViewModel.userList.count))")
            
            List(userViewModel.userList.indices, id: \.self) { index in
                let user = userViewModel.userList[index]
                Text("\(user.id) : \(user.name.first), \(user.name.last)")
            }
        }.onAppear {
            userViewModel.fetchUsers()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
