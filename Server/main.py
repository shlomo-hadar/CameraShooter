import tkinter as tk
import cv2
from PIL import ImageTk, Image
import socket


count = 0
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((socket.gethostname(), 1244))
s.listen(5)
dictOfWaitingClients = {}
countOfWaitingClients = 0
countOfClientsInServer = 0
waitingClientsStr = ""
startGameAtThisNumOfClients = 2
playerId = 1
IMAGE_PATH = 'images/background.jpg'
addresses = ["192.168.0.12", "192.168.0.14", "192.168.0.17", "192.168.0.18", "192.168.0.19"]


def able_to_start_a_game_check():
    global countOfWaitingClients
    if countOfWaitingClients == startGameAtThisNumOfClients:
        countOfWaitingClients -= startGameAtThisNumOfClients
        matchesText.insert(tk.INSERT, f"{list(dictOfWaitingClients.keys())[0]} and {list(dictOfWaitingClients.keys())[1]} are in a match.\n")
        for i in range(startGameAtThisNumOfClients):
            del dictOfWaitingClients[list(dictOfWaitingClients.keys())[0]]
        playersText.delete("1.0", "end")
        print(dictOfWaitingClients)


def update_waiting_list_text_area():
    global waitingClientsStr
    waitingClientsStr = ""
    playersText.delete("1.0", "end")
    for item in dictOfWaitingClients:
        waitingClientsStr += f"\t{dictOfWaitingClients[item]}\n"
    playersText.insert(tk.INSERT, waitingClientsStr)
    # print(dictOfWaitingClients)
    root.update()
    # playersText.insert(tk.INSERT, f"\t{address}\n")
    # playersText.


def add_player_to_waiting_list(address):
    global countOfWaitingClients, countOfClientsInServer, count2
    countOfWaitingClients += 1
    countOfClientsInServer += 1
    player = "Player"+str(countOfClientsInServer)
    dictOfWaitingClients[player] = f"{address} recieved as {player}"
    count2 += 1
    # playersText.insert(tk.INSERT, f"\t{address}\n")
    # playersText.
    update_waiting_list_text_area()
    able_to_start_a_game_check()


def get_request():

    clientsocket, address = s.accept()
    add_player_to_waiting_list(address)
    print(f"Connection from {address} has been established.")
    clientsocket.send(bytes("Hey there!!!", "utf-8"))
    clientsocket.close()


root = tk.Tk()

root.title('The Merit Snatcher Server')
screen_width = 2560  # root.winfo_screenwidth()2560
screen_height = 1440  # root.winfo_screenheight()1440

canvas = tk.Canvas(root, height=screen_width, width=screen_width, bg="red")
canvas.pack()

img = ImageTk.PhotoImage(Image.open(IMAGE_PATH).resize((screen_width, screen_height), Image.ANTIALIAS))
canvas.background = img  # Keep a reference in case this code is put in a function.
bg = canvas.create_image(0, 0, anchor=tk.NW, image=img)


NWFrame = tk.Frame(root, bg="red")
#NWFrame.place(x=0, y=0, width=screen_width, height=screen_height)
HeadlineLabel = tk.Label(root, text="The Merit Snatcher Server", bg="black", fg="grey90", font=("Arial", 25), pady=20)
HeadlineLabel.place(x=screen_width/2-200, y=screen_height/24)
HeadlineLabel = tk.Label(root, text="Players waiting for a match to start", bg="black", fg="grey90", font=("Arial", 18), pady=20)
HeadlineLabel.place(x=screen_width / 12, y=screen_height/7)
HeadlineLabel = tk.Label(root, text="Matches in progress", bg="black", fg="grey90", font=("Arial", 18), pady=20)
HeadlineLabel.place(x=screen_width/1.4-100,  y=screen_height/7)

playersText = tk.Text(root, pady=20)
# playersText.insert(tk.INSERT, "\tHello.....\n")
# playersText.insert(tk.END, "Bye Bye.....")
playersText.place(x=screen_width/12, y=screen_height/5)

matchesText = tk.Text(root, pady=20)
# matchesText.insert(tk.INSERT, "\tHello.....\n")
# matchesText.insert(tk.END, "Bye Bye.....")
matchesText.place(x=screen_width / 1.4-100, y=screen_height / 5)


# while True:
# now our endpoint knows about the OTHER endpoint.
try:
    pass
except cv2.error:
    print("shniki")
finally:
    print("The 'try except' is finished \U0001F603")
while 1:
    root.update()
    get_request()
