import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { Comment } from '../comment.model';

@Component({
  selector: 'app-comment-create',
  templateUrl: './comment-create.component.html',
  styleUrls: ['./comment-create.component.css']
})
export class CommentCreateComponent implements OnInit {

  enteredTitle = "";
  enteredContent = "";

  @Output() commentCreated = new EventEmitter<Comment>();

  constructor() { }

  ngOnInit(): void {
  }
  onAddComment(){
    const comment: Comment = {
      title: this.enteredTitle,
      content: this.enteredContent
    }
    this.commentCreated.emit(comment);
  }
}
