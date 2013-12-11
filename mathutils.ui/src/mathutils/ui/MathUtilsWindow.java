package mathutils.ui;

import mathutils.core.MathUtils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MathUtilsWindow {

	protected Shell shell;
	private Text inputText;
	private Text resultText;
	
	MathUtils mathUtils = new MathUtils();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MathUtilsWindow window = new MathUtilsWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("MathUtils Factorial");
		shell.setLayout(new GridLayout(3, false));
		
		Label lblInput = new Label(shell, SWT.NONE);
		lblInput.setText("input");
		
		inputText = new Text(shell, SWT.BORDER);
		inputText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Button btnCompute = new Button(shell, SWT.NONE);
		btnCompute.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (inputText.getText().trim().length() == 0) {
					resultText.setText("Empty input");
					return;
				}
				
				try {
					int inputAsInt = Integer.parseInt(inputText.getText());
					try {
						int result = mathUtils.factorial(inputAsInt);
						resultText.setText(result+"");
					} catch (IllegalArgumentException e1) {
						resultText.setText("Not a valid input");
					}
					
				} catch (NumberFormatException ex) {
					resultText.setText("Not a valid input");
				}
			}
		});
		btnCompute.setText("Compute");
		
		Label lblResult = new Label(shell, SWT.NONE);
		lblResult.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblResult.setText("result");
		
		resultText = new Text(shell, SWT.BORDER);
		resultText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);

	}
}