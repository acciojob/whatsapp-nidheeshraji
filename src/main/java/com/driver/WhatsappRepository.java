package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private int customGroupCount;
    private int messageId;


    public WhatsappRepository(){
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }

    public String createUser(String name, String mobile) {
        if(userMobile.contains(mobile))
        return "null";
        else
        {

            userMobile.add(mobile);

        }
        return "successfully Added";
    }

    public Group createGroup(List<User> users) {
        String name="";

        if(users.size()<=2)
        {
             name=users.get(1).getName();
        }
        else
            name="Group_count"+customGroupCount;

        Group group=new Group(name,users.size());
        groupUserMap.put(group,users);
        adminMap.put(group,users.get(0));
        customGroupCount++;

        return group;


    }

    public int createMessage(String content) {
        int ans=this.messageId;
        this.messageId++;
        return ans;
    }

    public int sendMessage(Message message, User sender, Group group) {
        if(!groupUserMap.containsKey(group))
            return -1;
        else if (groupUserMap.containsKey(group)) {
            {
               if(groupUserMap.get(group).contains(sender))
               {

                   List<Message>msg=groupMessageMap.get(group);
                   msg.add(message);
                   groupMessageMap.put(group,msg);
                   senderMap.put(message,sender);

                   return msg.size();
               }
               else
                   return -2;
            }

        }

     return 0;
    }

    public String changeAdmin(User approver, User user, Group group) {
        if(!adminMap.containsKey(group))
            return "Not_Exist";
        else if(adminMap.get(group).equals(approver))
        {
            if(groupUserMap.get(group).contains(user))
            {
                adminMap.put(group,user);
                return "Success";
            }
            else
                return "Not_participant";


        }

        return "not have rights";
    }

    public int removeUser(User user) {
        return 0;
    }

    public String findMessage(Date start, Date end, int k) {
        return null;
    }













    ////getters and setters


    public HashMap<Group, List<User>> getGroupUserMap() {
        return groupUserMap;
    }

    public void setGroupUserMap(HashMap<Group, List<User>> groupUserMap) {
        this.groupUserMap = groupUserMap;
    }

    public HashMap<Group, List<Message>> getGroupMessageMap() {
        return groupMessageMap;
    }

    public void setGroupMessageMap(HashMap<Group, List<Message>> groupMessageMap) {
        this.groupMessageMap = groupMessageMap;
    }

    public HashMap<Message, User> getSenderMap() {
        return senderMap;
    }

    public void setSenderMap(HashMap<Message, User> senderMap) {
        this.senderMap = senderMap;
    }

    public HashMap<Group, User> getAdminMap() {
        return adminMap;
    }

    public void setAdminMap(HashMap<Group, User> adminMap) {
        this.adminMap = adminMap;
    }

    public HashSet<String> getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(HashSet<String> userMobile) {
        this.userMobile = userMobile;
    }

    public int getCustomGroupCount() {
        return customGroupCount;
    }

    public void setCustomGroupCount(int customGroupCount) {
        this.customGroupCount = customGroupCount;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}
