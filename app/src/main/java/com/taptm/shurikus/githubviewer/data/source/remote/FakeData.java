package com.taptm.shurikus.githubviewer.data.source.remote;


import android.widget.ListView;

import com.taptm.shurikus.githubviewer.data.Repo;
import com.taptm.shurikus.githubviewer.data.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class FakeData {

    public static List<User> getFakeUsers(){
        List<User> users = new ArrayList<>();

        users.add(new User(22312214, "shurik236", "https://avatars.githubusercontent.com/u/22312214?v=3",
                "https://api.github.com/users/shurik236", "https://github.com/shurik236",
                "https://api.github.com/users/shurik236/repos"));

        users.add(new User(1052889, "shurik4545", "https://avatars.githubusercontent.com/u/1052889?v=3",
                "https://api.github.com/users/shurik4545", "https://github.com/shurik4545",
                "https://api.github.com/users/shurik4545/repos"));

        users.add(new User(3917774, "shumaojie", "https://avatars.githubusercontent.com/u/3917774?v=3",
                "https://api.github.com/users/shumaojie", "https://github.com/shumaojie",
                "https://api.github.com/users/shumaojie/repos"));

        return users;
    }

    public static HashMap<String, List<Repo>> getFakeRepos(){
        HashMap<String, List<Repo>> userRepos = new HashMap<>();


        List<Repo> repoList1 = new ArrayList<>();
        repoList1.add(new Repo(69785710, "course_project", "https://github.com/shurik236/course_project",
                null, 0, true, "Python", 0));
        repoList1.add(new Repo(70168376, "demo-task-1", "https://github.com/shurik236/demo-task-1",
                "Демонстрационная задача  «Сложить два числа»", 0, false, "Java", 2));
        repoList1.add(new Repo(70177558, "shurik236.github.io", "https://github.com/shurik236/shurik236.github.io",
                null, 15, false, "HTML", 1));
        userRepos.put("shurik236",repoList1);


        List<Repo> repoList2 = new ArrayList<>();
        repoList2.add(new Repo(7733492, "course_project", "https://github.com/shurik4545/jCAE",
                "Java Computer Aided Engineering", 0, true, "Java", 0));
        userRepos.put("shurik4545",repoList2);

        List<Repo> repoList3 = new ArrayList<>();
        repoList3.add(new Repo(51968591, "360yunpan", "https://github.com/shumaojie/360yunpan",
                "360YunPan Command-line tools, support: Linux Mac Windows", 0, true, "Python", 0));
        repoList3.add(new Repo(63676051, "3DStudy", "https://github.com/shumaojie/3DStudy",
                "通过C#来操作3D模型", 0, false, "C#", 0));
        userRepos.put("shumaojie", repoList3);

        return userRepos;
    }
}
