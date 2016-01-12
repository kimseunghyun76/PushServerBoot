package com.hellowd.server.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by Helloworld
 * User : USER
 * Date : 2016-01-12
 * Time : 오후 4:06
 * To change this template use File | Settings | File and Code Templates.
 */
@Component
public class ZookeeperServer  implements Watcher,AsyncCallback.StatCallback{

    private static Logger logger = LoggerFactory.getLogger(ZookeeperServer.class);

    /**
     * ZooKeeper
     */
    private ZooKeeper zk;

    @Autowired
    @Qualifier("zookeeperHostPort")
    private String zookeeperHostPort;

    @Autowired
    @Qualifier("zookeeperSessionTimeout")
    private int zookeeperSessionTimeout;

    @Autowired
    @Qualifier("zookeeperZnode")
    private String znode;

    @Autowired
    @Qualifier("zookeeperZnodeData")
    private String znodeData;

    @Autowired
    @Qualifier("tcpSocketAddress")
    private InetSocketAddress tcpPort;

    @Autowired
    @Qualifier("httpSocketAddress")
    private InetSocketAddress httpPort;


    public void start(){

        /*********************************************************
         * Zookeeper session start
         ********************************************************/

        try{
            if(!StringUtils.isEmpty(zookeeperHostPort))
                zk = new ZooKeeper(zookeeperHostPort,zookeeperSessionTimeout, this);

            logger.info("["+znode+", svc: "+tcpPort+", ctrl: "+httpPort+"] start.");

        }catch (IOException e) {
            logger.error(e.toString(),e);
        } catch (Exception e){
            logger.error("zookeeper 에러다 " + e.toString(),e);
        }
    }



    @Override
    public void process(WatchedEvent watchedEvent) {
        logger.info("Watcher Process가 시작되었습니다.");

        if(watchedEvent.getType() == Event.EventType.None){
            Event.KeeperState state = watchedEvent.getState();

            if(state == Event.KeeperState.SyncConnected){
                logger.info("주키퍼 세션이 (재)연결 되었습니다.");

                zk.exists("/S3P",false,this,null);

            }else if(state == Event.KeeperState.Disconnected){
                logger.warn("연결이 끊겼네요... 연결이 될때까지 계속 연결 시도를 하겠습니다.");
            }else if(state == Event.KeeperState.Expired){
                logger.warn("주키퍼 세션이 만료되었습니다. 이 서버는 세션을 재생성 할것입니다.");

                try{
                    if(zk != null)
                        zk.close();
                    if(!StringUtils.isEmpty(zookeeperHostPort))
                        zk = new ZooKeeper(zookeeperHostPort,zookeeperSessionTimeout, this);
                }catch (IOException | InterruptedException e){
                    logger.error(e.toString(), e);
                }
            }
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        logger.info("AsyncCallback.StatCallback의 processResult가 시작되었습니다.");
        KeeperException.Code code = KeeperException.Code.get(rc);

        if(code == KeeperException.Code.NONODE){
            try{
                zk.create("/S3P", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }catch (Exception e){
                logger.error(e.toString(),e);
            }
        }

        try{
            if(zk.exists(znode, false) == null){
                zk.create(znode, znodeData.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
            }
        }catch (Exception e){
            logger.error(e.toString(),e);
        }
    }



}
