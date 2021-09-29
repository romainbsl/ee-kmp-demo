//
// Created by Romain on 26/11/2020.
// Copyright (c) 2020 orgName. All rights reserved.
//

import Foundation
import shared

class UserViewModel: ObservableObject {
    @Published var userList = [UserDomain]()

    private let userRepository: UserRepository
    init(userRepository: UserRepository) {
        self.userRepository = userRepository
    }

    func fetchUsers() {
        userRepository.getAllUsers(completionHandler: { (users: [UserDomain]?, error: Error?) in
            if let userList = users {
                self.userList = userList
            }
            if let errorReal = error {
                print(errorReal)
            }
        })
    }
}
