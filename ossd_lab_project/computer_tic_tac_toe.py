import math

PLAYER_X = 'X'
PLAYER_O = 'O'
EMPTY = ' '

def print_board(board):
    for row in board:
        print(" | ".join(row))
        print("-" * 5)

def is_board_full(board):
    return all(EMPTY not in row for row in board)

def check_winner(board, player):
    for row in board:
        if all(cell == player for cell in row):
            return True
    for col in range(3):
        if all(board[row][col] == player for row in range(3)):
            return True
    if all(board[i][i] == player for i in range(3)) or all(board[i][2-i] == player for i in range(3)):
        return True
    return False

def get_available_moves(board):
    return [(i, j) for i in range(3) for j in range(3) if board[i][j] == EMPTY]

def minimax(board, depth, alpha, beta, maximizing_player):
    if check_winner(board, PLAYER_X):
        return 10 - depth
    elif check_winner(board, PLAYER_O):
        return depth - 10
    elif is_board_full(board):
        return 0
    
    if maximizing_player:
        max_eval = -math.inf
        for i, j in get_available_moves(board):
            board[i][j] = PLAYER_X
            eval = minimax(board, depth + 1, alpha, beta, False)
            board[i][j] = EMPTY
            max_eval = max(max_eval, eval)
            alpha = max(alpha, eval)
            if beta <= alpha:
                break
        return max_eval
    else:
        min_eval = math.inf
        for i, j in get_available_moves(board):
            board[i][j] = PLAYER_O
            eval = minimax(board, depth + 1, alpha, beta, True)
            board[i][j] = EMPTY
            min_eval = min(min_eval, eval)
            beta = min(beta, eval)
            if beta <= alpha:
                break
        return min_eval

def find_best_move(board):
    best_move = None
    best_eval = -math.inf
    for i, j in get_available_moves(board):
        board[i][j] = PLAYER_X
        eval = minimax(board, 0, -math.inf, math.inf, False)
        board[i][j] = EMPTY
        if eval > best_eval:
            best_eval = eval
            best_move = (i, j)
    return best_move

def play_tic_tac_toe():
    board = [[EMPTY]*3 for _ in range(3)]
    print("Welcome to Tic-Tac-Toe!")
    print_board(board)
    while not is_board_full(board):
        x, y = map(int, input("Enter your move (row col): ").split())
        if board[x][y] != EMPTY:
            print("Invalid move! Cell already occupied.")
            continue
        board[x][y] = PLAYER_O
        print_board(board)
        if check_winner(board, PLAYER_O):
            print("You win!")
            return
        if is_board_full(board):
            print("It's a draw!")
            return
        print("Computer's turn...")
        i, j = find_best_move(board)
        board[i][j] = PLAYER_X
        print_board(board)
        if check_winner(board, PLAYER_X):
            print("Computer wins!")
            return
        if is_board_full(board):
            print("It's a draw!")
            return

play_tic_tac_toe()