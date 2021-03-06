
// 8-5Dlg.cpp: 实现文件
//

#include "stdafx.h"
#include "8-5.h"
#include "8-5Dlg.h"
#include "afxdialogex.h"
#include"Wizard.h"
#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// 用于应用程序“关于”菜单项的 CAboutDlg 对话框

class CAboutDlg : public CDialogEx
{
public:
	CAboutDlg();

// 对话框数据
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_ABOUTBOX };
#endif

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持

// 实现
protected:
	DECLARE_MESSAGE_MAP()
};

CAboutDlg::CAboutDlg() : CDialogEx(IDD_ABOUTBOX)
{
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialogEx)
END_MESSAGE_MAP()


// CMy85Dlg 对话框



CMy85Dlg::CMy85Dlg(CWnd* pParent /*=nullptr*/)
	: CDialogEx(IDD_MY85_DIALOG, pParent)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CMy85Dlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
	DDX_Control(pDX, IDC_LIST1, m_listbox);
	DDX_Control(pDX, IDC_LIST3, m_programLangList);
	DDX_Control(pDX, name, myname);
	DDX_Control(pDX, IDC_COMBO2, mysubject);
	DDX_Control(pDX, IDC_COMBO3, mygoal);
}

BEGIN_MESSAGE_MAP(CMy85Dlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
//	ON_CBN_SELCHANGE(IDC_COMBO1, &CMy85Dlg::OnCbnSelchangeCombo1)
	//ON_EN_CHANGE(IDC_EDIT1, &CMy85Dlg::OnEnChangeEdit1)
	ON_EN_CHANGE(name, &CMy85Dlg::OnEnChangename)
	//ON_CBN_SELCHANGE(subject, &CMy85Dlg::OnCbnSelchangesubject)
	ON_CBN_SELCHANGE(IDC_COMBO2, &CMy85Dlg::OnCbnSelchangeCombo2)
	ON_LBN_SELCHANGE(IDC_LIST1, &CMy85Dlg::OnLbnSelchangeList1)
	ON_NOTIFY(LVN_ITEMCHANGED, IDC_LIST3, &CMy85Dlg::OnLvnItemchangedList3)
	ON_NOTIFY(NM_CLICK, IDC_LIST3, &CMy85Dlg::OnNMClickList3)
	ON_NOTIFY(NM_DBLCLK, IDC_LIST3, &CMy85Dlg::OnNMDblclkList3)
END_MESSAGE_MAP()


// CMy85Dlg 消息处理程序

BOOL CMy85Dlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// 将“关于...”菜单项添加到系统菜单中。

	// IDM_ABOUTBOX 必须在系统命令范围内。
	ASSERT((IDM_ABOUTBOX & 0xFFF0) == IDM_ABOUTBOX);
	ASSERT(IDM_ABOUTBOX < 0xF000);

	
	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != nullptr)
	{
		BOOL bNameValid;
		CString strAboutMenu;
		bNameValid = strAboutMenu.LoadString(IDS_ABOUTBOX);
		ASSERT(bNameValid);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
			pSysMenu->AppendMenu(MF_STRING, IDM_ABOUTBOX, strAboutMenu);
		}
	}

	// 设置此对话框的图标。  当应用程序主窗口不是对话框时，框架将自动
	//  执行此操作
	SetIcon(m_hIcon, TRUE);			// 设置大图标
	SetIcon(m_hIcon, FALSE);		// 设置小图标

	// TODO: 在此添加额外的初始化代码

	myname.SetWindowTextW(_T("姓名"));
	int count = 4;//记录列表存储的个数

	m_listbox.AddString(_T("添加"));  
	m_listbox.AddString(_T("插入"));  

	mysubject.AddString(_T("语文"));
	mysubject.AddString(_T("数学"));
	mysubject.AddString(_T("英语"));

	CRect rect;

	// 获取编程语言列表视图控件的位置和大小   
	m_programLangList.GetClientRect(&rect);

	// 为列表视图控件添加全行选中和栅格风格   
	m_programLangList.SetExtendedStyle(m_programLangList.GetExtendedStyle() | LVS_EX_FULLROWSELECT | LVS_EX_GRIDLINES);

	// 为列表视图控件添加三列   
	m_programLangList.InsertColumn(0, _T("姓名"), LVCFMT_CENTER, rect.Width() / 3, 0);
	m_programLangList.InsertColumn(1, _T("科目"), LVCFMT_CENTER, rect.Width() / 3, 1);
	m_programLangList.InsertColumn(2, _T("成绩"), LVCFMT_CENTER, rect.Width() / 3, 2);

	// 在列表视图控件中插入列表项，并设置列表子项文本   
	m_programLangList.InsertItem(0, _T("小明"));
	m_programLangList.SetItemText(0, 1, _T("语文"));
	m_programLangList.SetItemText(0, 2, _T("A"));
	m_programLangList.InsertItem(1, _T("小红"));
	m_programLangList.SetItemText(1, 1, _T("数学"));
	m_programLangList.SetItemText(1, 2, _T("C"));
	m_programLangList.InsertItem(0, _T("小李"));
	m_programLangList.SetItemText(0, 1, _T("英语"));
	m_programLangList.SetItemText(0, 2, _T("D"));
	m_programLangList.InsertItem(0, _T("小芳"));
	m_programLangList.SetItemText(0, 1, _T("语文"));
	m_programLangList.SetItemText(0, 2, _T("B"));


	return TRUE;  // 除非将焦点设置到控件，否则返回 TRUE
}

void CMy85Dlg::OnSysCommand(UINT nID, LPARAM lParam)
{
	if ((nID & 0xFFF0) == IDM_ABOUTBOX)
	{
		CAboutDlg dlgAbout;
		dlgAbout.DoModal();
	}
	else
	{
		CDialogEx::OnSysCommand(nID, lParam);
	}
}

// 如果向对话框添加最小化按钮，则需要下面的代码
//  来绘制该图标。  对于使用文档/视图模型的 MFC 应用程序，
//  这将由框架自动完成。

void CMy85Dlg::OnPaint()
{
	if (IsIconic())
	{
		CPaintDC dc(this); // 用于绘制的设备上下文

		SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

		// 使图标在工作区矩形中居中
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// 绘制图标
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialogEx::OnPaint();
	}
}

//当用户拖动最小化窗口时系统调用此函数取得光标
//显示。
HCURSOR CMy85Dlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}



void CMy85Dlg::OnCbnSelchangeCombo1()
{
	// TODO: 在此添加控件通知处理程序代码
}


void CMy85Dlg::OnEnChangeEdit1()
{
	// TODO:  如果该控件是 RICHEDIT 控件，它将不
	// 发送此通知，除非重写 CDialogEx::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。

	// TODO:  在此添加控件通知处理程序代码
	
}


void CMy85Dlg::OnEnChangename()
{
	// TODO:  如果该控件是 RICHEDIT 控件，它将不
	// 发送此通知，除非重写 CDialogEx::OnInitDialog()
	// 函数并调用 CRichEditCtrl().SetEventMask()，
	// 同时将 ENM_CHANGE 标志“或”运算到掩码中。

	// TODO:  在此添加控件通知处理程序代码

}


void CMy85Dlg::OnCbnSelchangesubject()
{
	// TODO: 在此添加控件通知处理程序代码
	
}


void CMy85Dlg::OnCbnSelchangeCombo2()
{
	// TODO: 在此添加控件通知处理程序代码
	/*m_cbExamble.AddString(L"chinese");
	m_cbExamble.AddString(L"math");
	m_cbExamble.AddString(L"english");
*/
}

int l = 0;
void CMy85Dlg::OnLbnSelchangeList1()
{
	// TODO: 在此添加控件通知处理程序代码

	int x = m_listbox.GetCurSel();//获取当前选中的项

	if (x == 1) {//添加
		//获取名字
		int p = m_listbox.GetCount()+2;
		CString s;
		myname.GetWindowTextW(s);
	
		m_programLangList.InsertItem(p, s);
		//获取科目
		CString c;
		mysubject.GetWindowTextW(c);
		m_programLangList.SetItemText(p, 1, c);
		//获取分数
		CString g;
		mygoal.GetWindowTextW(g);
		m_programLangList.SetItemText(p, 2, g);
	}
	
	if (x == 0) {//插入
		//获取名字
		CString s;
		myname.GetWindowTextW(s);
		m_programLangList.InsertItem(l, s);
		//获取科目
		CString c;
		mysubject.GetWindowTextW(c);
		m_programLangList.SetItemText(l, 1, c);
		//获取分数
		CString g;
		mygoal.GetWindowTextW(g);
		m_programLangList.SetItemText(l, 2, g);

	}
}


void CMy85Dlg::OnLvnItemchangedList3(NMHDR *pNMHDR, LRESULT *pResult)
{
	LPNMLISTVIEW pNMLV = reinterpret_cast<LPNMLISTVIEW>(pNMHDR);
	// TODO: 在此添加控件通知处理程序代码
	*pResult = 0;
	
}

//单击列表
void CMy85Dlg::OnNMClickList3(NMHDR *pNMHDR, LRESULT *pResult)
{
	LPNMITEMACTIVATE pNMItemActivate = reinterpret_cast<LPNMITEMACTIVATE>(pNMHDR);
	// TODO: 在此添加控件通知处理程序代码
	*pResult = 0;
	NMLISTVIEW *pNMListView = (NMLISTVIEW*)pNMHDR;
	if (-1 != pNMListView->iItem)        // 如果iItem不是-1，就说明有列表项被选择   
	{
		l = pNMListView->iItem;
	}
}

//双击列表
void CMy85Dlg::OnNMDblclkList3(NMHDR *pNMHDR, LRESULT *pResult)
{
	LPNMITEMACTIVATE pNMItemActivate = reinterpret_cast<LPNMITEMACTIVATE>(pNMHDR);
	// TODO: 在此添加控件通知处理程序代码
	*pResult = 0;
	NMLISTVIEW *pNMListView = (NMLISTVIEW*)pNMHDR;
	if (-1 != pNMListView->iItem)        // 如果iItem不是-1，就说明有列表项被选择   
	{
		m_programLangList.DeleteItem(pNMListView->iItem);
	}
}
