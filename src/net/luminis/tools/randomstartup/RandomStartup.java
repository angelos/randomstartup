package net.luminis.tools.randomstartup;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

public class RandomStartup {
    private final BundleContext m_bundleContext;

    public RandomStartup(BundleContext bundleContext) {
        m_bundleContext = bundleContext;
    }
    
    public void randomStartup(long seed, final PrintStream out, final PrintStream err) {
        final List<Bundle> bundles = getNonActiveBundles();
        
        Collections.shuffle(bundles, new Random(seed));
        
        new Thread("randomstart " + seed) {
            public void run() {
                startBundles(bundles, out, err);
            };
        }.start();
    }

    public void randomStartup(long seed) {
        randomStartup(seed, System.out, System.err);
    }

    private List<Bundle> getNonActiveBundles() {
        List<Bundle> bundles = new ArrayList<Bundle>();
        for (Bundle b : m_bundleContext.getBundles()) {
            if (b.getState() != Bundle.ACTIVE) {
                bundles.add(b);
            }
        }
        return bundles;
    }

    private void startBundles(List<Bundle> bundles, PrintStream out, PrintStream err) {
        for (Bundle b : bundles) {
            try {
                out.println("Starting bundle " + b.getBundleId() + ", " + b.getSymbolicName());
                b.start();
            } catch (BundleException e) {
                err.println("Error starting bundle " + b.getBundleId() + ", breaking startup.");
                e.printStackTrace(err);
                return;
            }
        }
    }

}
