package dk.robomenden.Robomendenapp.models;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "task_create")
    private boolean taskCreate;

    @Column(name = "task_delete")
    private boolean taskDelete;

    @Column(name = "task_open_modal_edit")
    private boolean taskOpenModalEdit;

    @Column(name = "task_edit")
    private boolean taskEdit;

    @Column(name = "client_create")
    private boolean clientCreate;

    @Column(name = "client_delete")
    private boolean clientDelete;

    @Column(name = "client_edit")
    private boolean clientEdit;

    @Column(name = "client_comments")
    private boolean clientComments;

    @Column(name = "client_write_comments")
    private boolean clientWriteComments;

    @Column(name = "client_comment_edit")
    private boolean clientCommentEdit;

    @Column(name = "client_gallery_add_files")
    private boolean clientGalleryAddFiles;

    @Column(name = "client_gallery_open_files")
    private boolean clientGalleryOpenFiles;

    @Column(name = "client_gallery_delete_files")
    private boolean clientGalleryDeleteFiles;

    @Column(name = "robot_client_add")
    private boolean robotClientAdd;

    @Column(name = "robot_client_edit")
    private boolean robotClientEdit;

    @Column(name = "robot_client_delete")
    private boolean robotClientDelete;

    @Column(name = "winter_task_add_and_edit")
    private boolean winterTaskAddAndEdit;

    @Column(name = "menu_bar_client")
    private boolean menuBarClient;

    @Column(name = "menu_bar_winter_task")
    private boolean menuBarWinterTask;

    @Column(name = "menu_bar_build_market")
    private boolean menuBarBuildMarket;

    @Column(name = "menu_bar_producer")
    private boolean menuBarProducer;

    @Column(name = "menu_bar_admin")
    private boolean menuBarAdmin;

    @Column(name = "menubaruser")
    private boolean menuBarUser;

    @Column(name = "user_add")
    private boolean userAdd;

    @Column(name = "user_edit")
    private boolean userEdit;

    @Column(name = "user_edit_password")
    private boolean userEditPassword;

    @Column(name = "user_edit_role")
    private boolean userEditRole;

    @Column(name = "comment_check")
    private boolean commentCheck;

    public Role(int id, String roleName, boolean commentCheck, boolean userEditRole, boolean userEditPassword, boolean userAdd, boolean userEdit, boolean menuBarUser, boolean menuBarAdmin, boolean menuBarBuildMarket, boolean menuBarProducer, boolean menuBarWinterTask, boolean menuBarClient, boolean winterTaskAddAndEdit, boolean robotClientEdit, boolean robotClientDelete, boolean robotClientAdd, boolean clientGalleryDeleteFiles, boolean clientGalleryAddFiles, boolean clientGalleryOpenFiles, boolean clientCommentEdit, boolean taskCreate, boolean taskDelete, boolean taskOpenModalEdit, boolean taskEdit, boolean clientCreate, boolean clientDelete, boolean clientEdit, boolean clientComments, boolean clientWriteComments) {
        this.id = id;
        this.robotClientAdd = robotClientAdd;
        this.commentCheck = commentCheck;
        this.userEditRole = userEditRole;
        this.userEditPassword = userEditPassword;
        this.userEdit = userEdit;
        this.userAdd = userAdd;
        this.menuBarUser = menuBarUser;
        this.menuBarAdmin = menuBarAdmin;
        this.menuBarProducer = menuBarProducer;
        this.menuBarBuildMarket = menuBarBuildMarket;
        this.menuBarWinterTask = menuBarWinterTask;
        this.menuBarClient = menuBarClient;
        this.winterTaskAddAndEdit = winterTaskAddAndEdit;
        this.robotClientEdit = robotClientEdit;
        this.robotClientDelete = robotClientDelete;
        this.clientGalleryAddFiles = clientGalleryAddFiles;
        this.clientGalleryOpenFiles = clientGalleryOpenFiles;
        this.clientGalleryDeleteFiles = clientGalleryDeleteFiles;
        this.clientCommentEdit = clientCommentEdit;
        this.roleName = roleName;
        this.taskCreate = taskCreate;
        this.taskDelete = taskDelete;
        this.taskOpenModalEdit = taskOpenModalEdit;
        this.taskEdit = taskEdit;
        this.clientCreate = clientCreate;
        this.clientDelete = clientDelete;
        this.clientEdit = clientEdit;
        this.clientComments = clientComments;
        this.clientWriteComments = clientWriteComments;
    }


    public Role() {

    }

    public boolean isClientCreate() {
        return clientCreate;
    }

    public void setClientCreate(boolean clientCreate) {
        this.clientCreate = clientCreate;
    }

    public boolean isClientDelete() {
        return clientDelete;
    }

    public void setClientDelete(boolean clientDelete) {
        this.clientDelete = clientDelete;
    }

    public boolean isClientEdit() {
        return clientEdit;
    }

    public void setClientEdit(boolean clientEdit) {
        this.clientEdit = clientEdit;
    }

    public boolean isClientComments() {
        return clientComments;
    }

    public void setClientComments(boolean clientComments) {
        this.clientComments = clientComments;
    }

    public boolean isClientWriteComments() {
        return clientWriteComments;
    }

    public void setClientWriteComments(boolean clientWriteComments) {
        this.clientWriteComments = clientWriteComments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isTaskCreate() {
        return taskCreate;
    }

    public void setTaskCreate(boolean taskCreate) {
        this.taskCreate = taskCreate;
    }

    public boolean isTaskDelete() {
        return taskDelete;
    }

    public void setTaskDelete(boolean taskDelete) {
        this.taskDelete = taskDelete;
    }

    public boolean isTaskOpenModalEdit() {
        return taskOpenModalEdit;
    }

    public void setTaskOpenModalEdit(boolean taskOpenModalEdit) {
        this.taskOpenModalEdit = taskOpenModalEdit;
    }

    public boolean isTaskEdit() {
        return taskEdit;
    }

    public void setTaskEdit(boolean taskEdit) {
        this.taskEdit = taskEdit;
    }

    public boolean isClientCommentEdit() {
        return clientCommentEdit;
    }

    public void setClientCommentEdit(boolean clientCommentEdit) {
        this.clientCommentEdit = clientCommentEdit;
    }

    public boolean isClientGalleryAddFiles() {
        return clientGalleryAddFiles;
    }

    public void setClientGalleryAddFiles(boolean clientGalleryAddFiles) {
        this.clientGalleryAddFiles = clientGalleryAddFiles;
    }

    public boolean isClientGalleryOpenFiles() {
        return clientGalleryOpenFiles;
    }

    public void setClientGalleryOpenFiles(boolean clientGalleryOpenFiles) {
        this.clientGalleryOpenFiles = clientGalleryOpenFiles;
    }

    public boolean isClientGalleryDeleteFiles() {
        return clientGalleryDeleteFiles;
    }

    public void setClientGalleryDeleteFiles(boolean clientGalleryDeleteFiles) {
        this.clientGalleryDeleteFiles = clientGalleryDeleteFiles;
    }

    public boolean isRobotClientAdd() {
        return robotClientAdd;
    }

    public void setRobotClientAdd(boolean robotClientAdd) {
        this.robotClientAdd = robotClientAdd;
    }

    public boolean isRobotClientEdit() {
        return robotClientEdit;
    }

    public void setRobotClientEdit(boolean robotClientEdit) {
        this.robotClientEdit = robotClientEdit;
    }

    public boolean isRobotClientDelete() {
        return robotClientDelete;
    }

    public void setRobotClientDelete(boolean robotClientDelete) {
        this.robotClientDelete = robotClientDelete;
    }

    public boolean isWinterTaskAddAndEdit() {
        return winterTaskAddAndEdit;
    }

    public void setWinterTaskAddAndEdit(boolean winterTaskAddAndEdit) {
        this.winterTaskAddAndEdit = winterTaskAddAndEdit;
    }

    public boolean isMenuBarClient() {
        return menuBarClient;
    }

    public void setMenuBarClient(boolean menuBarClient) {
        this.menuBarClient = menuBarClient;
    }


    public boolean isMenuBarWinterTask() {
        return menuBarWinterTask;
    }

    public void setMenuBarWinterTask(boolean menuBarWinterTask) {
        this.menuBarWinterTask = menuBarWinterTask;
    }

    public boolean isMenuBarBuildMarket() {
        return menuBarBuildMarket;
    }

    public void setMenuBarBuildMarket(boolean menuBarBuildMarket) {
        this.menuBarBuildMarket = menuBarBuildMarket;
    }

    public boolean isMenuBarProducer() {
        return menuBarProducer;
    }

    public void setMenuBarProducer(boolean menuBarProducer) {
        this.menuBarProducer = menuBarProducer;
    }


    public boolean isMenuBarAdmin() {
        return menuBarAdmin;
    }

    public void setMenuBarAdmin(boolean menuBarAdmin) {
        this.menuBarAdmin = menuBarAdmin;
    }

    public boolean isMenuBarUser() {
        return menuBarUser;
    }

    public void setMenuBarUser(boolean menuBarUser) {
        this.menuBarUser = menuBarUser;
    }

    public boolean isUserAdd() {
        return userAdd;
    }

    public void setUserAdd(boolean userAdd) {
        this.userAdd = userAdd;
    }

    public boolean isUserEdit() {
        return userEdit;
    }

    public void setUserEdit(boolean userEdit) {
        this.userEdit = userEdit;
    }

    public boolean isUserEditPassword() {
        return userEditPassword;
    }

    public void setUserEditPassword(boolean userEditPassword) {
        this.userEditPassword = userEditPassword;
    }

    public boolean isUserEditRole() {
        return userEditRole;
    }

    public void setUserEditRole(boolean userEditRole) {
        this.userEditRole = userEditRole;
    }

    public boolean isCommentCheck() {
        return commentCheck;
    }

    public void setCommentCheck(boolean commentCheck) {
        this.commentCheck = commentCheck;
    }
}
