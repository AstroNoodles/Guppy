import random, sys

def genericReply():
  replies = ["Pretty interesting, ain't it?", "That's pretty good", "Nice.", "Think about it more.", "You sure?", "Great.", "Could you try another situation."]
  random.shuffle(replies)
  return random.choice(replies)
  
def again():
  ans = raw_input("Another hard decision? (y/n)")
  while True:
    if ans.startswith("n"):
      sys.exit(0)
    elif ans.startswith("y"):
      break


values = {
  "!" : "Calm down... Everything will be all right!",
  "angry" : "Calm down... Everything will be all right!",
  "sad" : "Calm down... Everything will be all right!",
  "argument" : "Calm down... Everything will be all right!",
  "love" : "Just do it!",
  "banana" : "Bananas are a good source for fruit",
  "life" : "Life is a mystery in of itself",
  "Isaac Newton" : "Remember the three gravitational laws!",
  "George Washington" : "George Washington is the first president of the U.S.A.",
  "bacon" : "Delicious!",
  "friend" : "The friends are the people that truly care for you.",
  "foe": "The foes are the ones that exploit you. Peacefully resist them.",
  "?" : "Ask yourself the question again in your own terms.",
  "flight" : "Don't get sick!",
  "plane" : "Did you evaluate the rules of flight?",
  "password" : "I'd be ashamed if your passcode was your birthday, 1234 or your name",
  "email" : "Is it secure?",
  "challenge" : "One word: persevereance",
  "died" : "I'm so sorry. Can you recover from the experience?",
  "burn" : "Stay away from fire at all times..."
}

unknown = []

while True:
  s = raw_input("What is your situation?");
  for word in values:
    if word in s:
      print values.get(word) + "\n"
      break
  else:
    print genericReply()
    unknown.add(s)
  again()  
    
