using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Web.Script.Serialization;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace CoachServer
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public static String TicketStatusPending = "Chờ thanh toán";
        public static String TicketStatusCancel = "Đã hủy";
        public static String TicketStatusPayed = "Đã thanh toán";
        public ObservableCollection<Ticket> ticketList { get; set; }
        public ObservableCollection<Ticket> getTicketList()
        {
            ObservableCollection<Ticket> list = new ObservableCollection<Ticket>();
            Dictionary<string, object> jsonTList;
            string json;
            json = FBGet("https://carservices-cb0bd.firebaseio.com/TicketList.json");
            jsonTList = JsonConvert.DeserializeObject<Dictionary<string, object>>(json);
            foreach (var o in jsonTList)
            {
                Ticket t = JsonConvert.DeserializeObject<Ticket>(o.Value.ToString());
                t.id = o.Key;
                list.Add(t);
                //Console.WriteLine(t.id + " - " + t.seatNumber + " - " + t.status);
            }
            return list;
        }
        public ObservableCollection<Coach> coachList { get; set; }
        public ObservableCollection<Coach> getCoachList()
        {
            ObservableCollection<Coach> list = new ObservableCollection<Coach>();
            Dictionary<string, object> jsonTList;
            string json;
            json = FBGet("https://carservices-cb0bd.firebaseio.com/CoachList.json");
            jsonTList = JsonConvert.DeserializeObject<Dictionary<string, object>>(json);
            foreach (var o in jsonTList)
            {
                Coach t = JsonConvert.DeserializeObject<Coach>(o.Value.ToString());
                t.id = o.Key;
                list.Add(t);
                //Console.WriteLine(t.id + " - " + t.seatNumber + " - " + t.status);
            }
            return list;
        }
        public ObservableCollection<Company> companyList { get; set; }
        public ObservableCollection<Company> getCompanyList()
        {
            ObservableCollection<Company> list = new ObservableCollection<Company>();
            Dictionary<string, object> jsonTList;
            string json;
            json = FBGet("https://carservices-cb0bd.firebaseio.com/Company.json");
            jsonTList = JsonConvert.DeserializeObject<Dictionary<string, object>>(json);
            foreach (var o in jsonTList)
            {
                Company t = JsonConvert.DeserializeObject<Company>(o.Value.ToString());
                t.id = o.Key;
                list.Add(t);
            }
            return list;
        }
        public static string FBGet(string url)
        {
            string result = "";
            var request = WebRequest.CreateHttp(url);
            request.Method = "GET";
            request.ContentType = "application/json";
            var response = request.GetResponse();
            result = (new StreamReader(response.GetResponseStream())).ReadToEnd();
            return result;
        }
        public static void FBPush(string url, string s)
        {
            byte[] data = Encoding.UTF8.GetBytes(s);
            var request = WebRequest.CreateHttp(url);
            request.Method = "POST";
            request.ContentType = "application/json";
            request.ContentLength = data.Length;
            request.GetRequestStream().Write(data, 0, data.Length);
            var response = request.GetResponse();
            var json = (new StreamReader(response.GetResponseStream())).ReadToEnd();
        }
        public static void FBSet(string url, string s)
        {
            byte[] data = Encoding.UTF8.GetBytes(s);
            var request = WebRequest.CreateHttp(url);
            request.Method = "PUT";
            request.ContentType = "application/json";
            request.ContentLength = data.Length;
            request.GetRequestStream().Write(data, 0, data.Length);
            var response = request.GetResponse();
            var json = (new StreamReader(response.GetResponseStream())).ReadToEnd();
        }
        public static void FBDel(string url)
        {
            var request = WebRequest.CreateHttp(url);
            request.Method = "DELETE";
            var response = request.GetResponse();
            var json = (new StreamReader(response.GetResponseStream())).ReadToEnd();
        }
        public static void FBUpdate(string url, string s)
        {
            byte[] data = Encoding.UTF8.GetBytes(s);
            var request = WebRequest.CreateHttp(url);
            request.Method = "PATCH";
            request.ContentType = "application/json";
            request.ContentLength = data.Length;
            request.GetRequestStream().Write(data, 0, data.Length);
            var response = request.GetResponse();
            var json = (new StreamReader(response.GetResponseStream())).ReadToEnd();
        }
        public static long getCurrentMiliseconds()
        {
            DateTime dt1970 = new DateTime(1970, 1, 1);
            DateTime current = DateTime.Now;
            TimeSpan span = current - dt1970;
            return (long)span.TotalMilliseconds;
        }
        public long time;
        public bool isEdit = false;
        public bool isAdd = false;
        public Coach currentCoach;
        public Coach beforeCoach;
        public MainWindow()
        {
            time = getCurrentMiliseconds();
            InitializeComponent();

            ticketList = getTicketList();
            dg1.ItemsSource = null;
            dg1.ItemsSource = ticketList;

            coachList = getCoachList();
            dg2.ItemsSource = null;
            dg2.ItemsSource = coachList;

            companyList = getCompanyList();
            cbb.ItemsSource = companyList;

            //for (int i=0; i<8; i++)
            //{
            //    Ticket tic = new Ticket(i+1);
            //    tic.id = "QDGYNKSA1" + i;
            //    tic.orderTime = getCurrentMiliseconds();
            //    tic.status = "Chờ thanh toán";
                
            //    FBUpdate("https://carservices-cb0bd.firebaseio.com/TicketList/" + tic.id + ".json", JsonConvert.SerializeObject(tic));
            //}

            //Thread t = new Thread(new ThreadStart(Countdown));
            //t.Start();
        }
        
        public void Countdown()
        {
            while (true)
            {
                ObservableCollection<Ticket> list = getTicketList();
                long current = getCurrentMiliseconds();
                foreach(var t in list)
                {
                    if (t.status != TicketStatusCancel && current - t.orderTime > 86400000)
                    {
                        t.status = TicketStatusCancel;
                        if (t.id != null && t.id != "")
                        FBUpdate("https://carservices-cb0bd.firebaseio.com/TicketList/" + t.id + ".json", JsonConvert.SerializeObject(t));
                    }
                }
                try
                {
                    this.Dispatcher.Invoke(() =>
                    {
                        dg1.ItemsSource = null;
                        dg1.ItemsSource = list;
                    });
                }
                catch (Exception e) { return; }
                Thread.Sleep(1000);
            }
        } 

        private void PayButton(object sender, RoutedEventArgs e)
        {
            Button b = (Button)sender;
            string id = ((Ticket)b.DataContext).id;
            foreach(var t in ticketList)
            {
                if (t.id == id)
                {
                    t.status = TicketStatusPayed;
                    FBUpdate("https://carservices-cb0bd.firebaseio.com/TicketList/" + t.id + ".json", JsonConvert.SerializeObject(t));
                    dg1.ItemsSource = null;
                    dg1.ItemsSource = ticketList;
                    break;
                }
            }
        }

        private void CancelButton(object sender, RoutedEventArgs e)
        {
            Button b = (Button)sender;
            string id = ((Ticket)b.DataContext).id;
            foreach (var t in ticketList)
            {
                if (t.id == id)
                {
                    t.status = TicketStatusCancel;
                    dg1.ItemsSource = null;
                    dg1.ItemsSource = ticketList;
                    FBUpdate("https://carservices-cb0bd.firebaseio.com/TicketList/" + t.id + ".json", JsonConvert.SerializeObject(t));
                }
            }
        }

        private void AddCoach(object sender, RoutedEventArgs e)
        {
            isAdd = true;
            canvas.Visibility = Visibility.Collapsed;
            edit.Visibility = Visibility.Visible;
            noEdit.Visibility = Visibility.Collapsed;
            Coach c = new Coach();
            coachList.Add(c);
            dg2.SelectedItem = c;
            currentCoach = c;
        }

        private void DelCoach(object sender, RoutedEventArgs e)
        {           
            if (currentCoach != null)
            {
                FBDel("https://carservices-cb0bd.firebaseio.com/CoachList/" + currentCoach.id + ".json");
                coachList.Remove(currentCoach);
            }
        }

        private void EditCoach(object sender, RoutedEventArgs e)
        {
            if (dg2.SelectedItem != null)
            {
                isEdit = true;
                beforeCoach = new Coach();
                beforeCoach.copyCoach(currentCoach);
                canvas.Visibility = Visibility.Collapsed;
                edit.Visibility = Visibility.Visible;
                noEdit.Visibility = Visibility.Collapsed;
            }
        }

        private void SaveCoach(object sender, RoutedEventArgs e)
        {
            
            long num = currentCoach.timeStart;
            TimeSpan span = TimeSpan.FromMilliseconds(num);
            DateTime time = new DateTime(1970, 1, 1) + span;
            Console.WriteLine("string:" + time.ToString("dd-MM-yyyy hh:mm"));
            String ts = time.ToString("dd-MM-yyyy hh:mm");
            if (isEdit == true)
            {
                
                isEdit = false;
                FBDel("https://carservices-cb0bd.firebaseio.com/CoachList/" + currentCoach.id + ".json");
                currentCoach.id = currentCoach.from + "-" + currentCoach.to + "-" + ts + "-" + currentCoach.companyId;
                string s = JsonConvert.SerializeObject(currentCoach);
                FBSet("https://carservices-cb0bd.firebaseio.com/CoachList/" + currentCoach.id + ".json", s);
                
            }
            else if (isAdd == true)
            {
                isAdd = false;
                currentCoach.id = currentCoach.from + "-" + currentCoach.to + "-" + ts + "-" + currentCoach.companyId;
                string s = JsonConvert.SerializeObject(currentCoach);
                FBSet("https://carservices-cb0bd.firebaseio.com/CoachList/" + currentCoach.id + ".json", s);
            }
            
            canvas.Visibility = Visibility.Visible;
            edit.Visibility = Visibility.Collapsed;
            noEdit.Visibility = Visibility.Visible;
        }

        private void Cancel(object sender, RoutedEventArgs e)
        {
            if (isAdd == true)
            {
                coachList.Remove(currentCoach);
                isAdd = false;
            }
            else if (isEdit == true)
            {
                for (int i=0; i<coachList.Count; i++)
                {
                    if (coachList[i].id == currentCoach.id)
                    {
                        coachList[i].copyCoach(beforeCoach);
                        dg2.ItemsSource = null;
                        dg2.ItemsSource = coachList;
                    }
                }
                isEdit = false;
            }
            canvas.Visibility = Visibility.Visible;
            edit.Visibility = Visibility.Collapsed;
            noEdit.Visibility = Visibility.Visible;
        }
        private void dg2_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            //if (isEdit == true || isAdd == true)
            //{
            //    dg2.SelectedItem = currentCoach;
            //}
            currentCoach = (Coach)dg2.SelectedItem;
            //Console.WriteLine("he"currentCoach.id);
        }

        private void Quit(object sender, RoutedEventArgs e)
        {
            Login l = new Login();
            l.Show();
            this.Close();
        }
    }
    public class Coach
    {
        public String id;
        public String from { get; set; }
        public String to { get; set; }
        public String journey { get; set; }
        public String companyId { get; set; }
        public int price { get; set; }
        public long timeStart { get; set; }
        public long timeEnd { get; set; }
        public int vacantSeats { get; set; }
        public String stars { get; set; }
        public String type { get; set; }
        public String[] ticketList { get; set; }
        public Coach()
        {
            from = "TPHCM";
            to = "HN";
            type = "Xe 16 chỗ";
            timeStart = 1525428535056;
            timeEnd = 1525438535056;
            companyId = "Thịnh phát";
            price = 0;
        }
        public void copyCoach(Coach coach)
        {
            id = coach.id;
            from = coach.from;
            journey = coach.journey;
            companyId = coach.companyId;
            price = coach.price;
            timeEnd = coach.timeEnd;
            timeStart = coach.timeStart;
            vacantSeats = coach.vacantSeats;
            stars = coach.stars;
            type = coach.type;
            //for(int i=0; i<ticketList.Length; i++)
            //{
            //    ticketList[i] = coach.ticketList[i];
            //}
        }
    }
    public class Company
    {
        public String id { get; set; }
        public String danhGiaChatLuongPhucVu { get; set; }
        public String danhGiaChatLuongXe { get; set; }
        public String danhGiaChung { get; set; }
        public String danhGiaDungGio { get; set; }
        public String tenNhaXe { get; set; }
    }
    public class User
    {
        public String id { get; set; }
        public String pass { get; set; }
        public String email { get; set; }
        public String number { get; set; }
        public String avatar { get; set; }
        public String rule { get; set; }
        //public List<String> ticketList { get; set; }
        public User(string e)
        {
            this.email = e;
            this.pass = "pass";
            this.number = "";
            this.avatar = "";
        }
    }
    public class Ticket
    {
        public String id { get; set; }
        public String status { get; set; }
        public int seatNumber { get; set; }
        public long orderTime { get; set; }
        public Ticket(int n)
        {
            this.status = MainWindow.TicketStatusPending;
            this.seatNumber = n;
        }
    }
    public class PayOrCancelConverter : IValueConverter
    {
        public object Convert(object value, Type targetType, object parameter, System.Globalization.CultureInfo culture)
        {
            string s = value.ToString();
            if (s == MainWindow.TicketStatusPending)
            {
                return Visibility.Visible;
            }
            else return Visibility.Hidden;
        }

        public object ConvertBack(object value, Type targetType, object parameter, System.Globalization.CultureInfo culture)
        {
            return 0;
        }
    }
    public class DatetimeConverter : IValueConverter
    {
        public object Convert(object value, Type targetType, object parameter, System.Globalization.CultureInfo culture)
        {
            long s = long.Parse(value.ToString());
            TimeSpan span = TimeSpan.FromMilliseconds(s);
            DateTime time = new DateTime(1970, 1, 1) + span;
            Console.WriteLine("string:" + time.ToString("dd/MM/yyyy hh:mm"));
            return time.ToString("dd/MM/yyyy hh:mm");
            
        }

        public object ConvertBack(object value, Type targetType, object parameter, System.Globalization.CultureInfo culture)
        {
            string s = value.ToString();
            DateTime time = DateTime.ParseExact(s, "dd/MM/yyyy HH:mm", System.Globalization.CultureInfo.InvariantCulture);
            Console.WriteLine("mili:" + time.ToFileTimeUtc());
            return time.ToFileTimeUtc();
        }
    }
}
