package cn.lh.learnproject.design_model.composite;

import java.util.ArrayList;
import java.util.List;

//抽象构建  模拟杀毒软件架构
public interface AbstractFile {
    void killVirus();//杀毒
}


class Test {
    public static void main(String[] args) {
        AbstractFile f1, f2, f3, f4, f5, f6;
        f1 = new ImageFile("她的头像");
        f2 = new ImageFile("她的自拍");
        f3 = new VideoFile("她的小视频");
        f4 = new VideoFile("她的抖音视频");
        f5 = new ImageFile("她的家人合照");
        f6 = new VideoFile("她的跳舞视频");
        Folder folder = new Folder("激情文件夹");
        folder.add(f1);
        folder.add(f2);
        folder.add(f3);
        folder.add(f4);
        folder.add(f5);
        folder.add(f6);
        folder.killVirus();
    }
}


class ImageFile implements AbstractFile {

    private String name;

    public ImageFile(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("图像文件" + name + "，进行查杀");
    }
}

class VideoFile implements AbstractFile {

    private String name;

    public VideoFile(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("视频文件" + name + "，进行查杀");
    }
}

//容器
class Folder implements AbstractFile {
    private String name;
    //容器，用来存放子节点
    private List<AbstractFile> list = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public void killVirus() {
        System.out.println("文件夹name" + name + "，进行查杀");
        for (AbstractFile file : list) {
            file.killVirus();
        }
    }


    public void add(AbstractFile file) {
        list.add(file);
    }

    public void remove(AbstractFile file) {
        list.remove(file);
    }

    public AbstractFile getChild(int index) {
        return list.get(index);
    }
}