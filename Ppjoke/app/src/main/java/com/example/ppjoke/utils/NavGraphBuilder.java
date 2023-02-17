package com.example.ppjoke.utils;

import android.content.ComponentName;

import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;

import com.example.ppjoke.model.Destination;

import java.util.HashMap;

public class NavGraphBuilder {

    public static void build(NavController controller) {
        NavigatorProvider provider = controller.getNavigatorProvider();

        FragmentNavigator fragmentNavigator = provider.getNavigator(FragmentNavigator.class);
        ActivityNavigator activityNavigator = provider.getNavigator(ActivityNavigator.class);
        NavGraph navGraph = new NavGraph(new NavGraphNavigator(provider));

        HashMap<String, Destination> destConfig = AppConfig.getsDestConfig();
        for (Destination value : destConfig.values()) {
            if (value.isFragement) {
                FragmentNavigator.Destination destination = fragmentNavigator.createDestination();
                destination.setClassName(value.className);
                destination.setId(value.id);
                destination.addDeepLink(value.pageUrl);
                navGraph.addDestination(destination);
            } else {
                ActivityNavigator.Destination destinationActivity = activityNavigator.createDestination();
                destinationActivity.setId(value.id);
                destinationActivity.setComponentName(new ComponentName(AppGlobals.getApplication().getPackageName(), value.className));
                destinationActivity.addDeepLink(value.pageUrl);
                navGraph.addDestination(destinationActivity);
            }
            //给APP页面导航结果图 设置一个默认的展示页的id
            if (value.asStarter) {
                navGraph.setStartDestination(value.id);
            }
        }
        controller.setGraph(navGraph);
    }
}
