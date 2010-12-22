package net.luminis.tools.randomstartup;

import org.apache.felix.shell.Command;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private ServiceRegistration m_commandService;

    @Override
    public void start(BundleContext context) throws Exception {
        RandomStartup startup = new RandomStartup(context);
        
        RandomStartupCommand command = new RandomStartupCommand(startup);
        m_commandService = context.registerService(Command.class.getName(), command, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        m_commandService.unregister();
    }

}
