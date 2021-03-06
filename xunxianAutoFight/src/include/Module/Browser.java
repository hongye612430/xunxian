package include.Module;

import include.Command;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserAdapter;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserListener;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowOpeningEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;
/**
 * 所依赖jar包：DJNativeSwing-SWTDemo.jar
 * @author 寻仙www.xnx3.com
 *
 */
public class Browser extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 行分隔符
	final static public String LS = System.getProperty("line.separator", "\n");

	// 文件分割符
	final static public String FS = System.getProperty("file.separator", "\\");

	//以javascript脚本获得网页全屏后大小
	final static StringBuffer jsDimension;
	
	static {
		jsDimension = new StringBuffer();
		jsDimension.append("var width = 0;").append(LS);
		jsDimension.append("var height = 0;").append(LS);
		jsDimension.append("if(document.documentElement) {").append(LS);
		jsDimension.append(
						"  width = Math.max(width, document.documentElement.scrollWidth);")
				.append(LS);
		jsDimension.append(
						"  height = Math.max(height, document.documentElement.scrollHeight);")
				.append(LS);
		jsDimension.append("}").append(LS);
		jsDimension.append("if(self.innerWidth) {").append(LS);
		jsDimension.append("  width = Math.max(width, self.innerWidth);")
				.append(LS);
		jsDimension.append("  height = Math.max(height, self.innerHeight);")
				.append(LS);
		jsDimension.append("}").append(LS);
		jsDimension.append("if(document.body.scrollWidth) {").append(LS);
		jsDimension.append(
				"  width = Math.max(width, document.body.scrollWidth);")
				.append(LS);
		jsDimension.append(
				"  height = Math.max(height, document.body.scrollHeight);")
				.append(LS);
		jsDimension.append("}").append(LS);
		jsDimension.append("return width + ':' + height;");
	}
	
	public Browser(){
		
	}
	
	public Browser(final String url,final int maxWidth,final int maxHeight) {
		super(new BorderLayout());
		JPanel webBrowserPanel = new JPanel(new BorderLayout());
		final JWebBrowser webBrowser = new JWebBrowser(null);
		webBrowser.setBarsVisible(false);
		webBrowser.navigate(url);
		webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
		add(webBrowserPanel, BorderLayout.CENTER);

		webBrowser.addHierarchyListener(new HierarchyListener() {
			@Override
			public void hierarchyChanged(HierarchyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		webBrowser.addHierarchyBoundsListener(new HierarchyBoundsListener() {
			
			@Override
			public void ancestorResized(HierarchyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void ancestorMoved(HierarchyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		webBrowser.addWebBrowserListener(new WebBrowserListener() {
			
			@Override
			public void windowWillOpen(WebBrowserWindowWillOpenEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void windowOpening(WebBrowserWindowOpeningEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void titleChanged(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void statusChanged(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void locationChanging(WebBrowserNavigationEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void locationChanged(WebBrowserNavigationEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void locationChangeCanceled(WebBrowserNavigationEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void loadingProgressChanged(WebBrowserEvent arg0) {
				// TODO Auto-generated method stub
//				System.out.println("loading-change");
			}
			
			@Override
			public void commandReceived(WebBrowserCommandEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 4));

		webBrowser.addWebBrowserListener(new WebBrowserAdapter() {

			// 监听加载进度
			public void loadingProgressChanged(WebBrowserEvent e) {
				// 当加载完毕时
				if (e.getWebBrowser().getLoadingProgress() == 100) {
					//加载完毕
				}
			}
		}

		);
		add(panel, BorderLayout.SOUTH);

	}

	/**
	 * 
	 * @param url http://www.xnx3.com
	 */
	public void loadUrl(final String url,final int x,final int y,final int width,final int height){
		NativeInterface.open();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// SWT组件转Swing组件，不初始化父窗体将无法启动webBrowser
				JFrame frame = new JFrame("公告说明-"+Command.title);
				// 加载google，最大保存为640x480的截图
				frame.getContentPane().add(new Browser(url, width, height),BorderLayout.CENTER);
				frame.setBounds(x, y,width,height);
				// 仅初始化，但不显示
//				frame.invalidate();
//				frame.pack(); 
				frame.setVisible(true);
			}
		});
//		NativeInterface.runEventPump();
		
	}
	
	public static void main(String[] args) {
		new Browser().loadUrl("http://wap.xnx3.com",200,200,300,600);
	}

}
