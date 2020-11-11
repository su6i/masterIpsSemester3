import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CommentService } from '../comment.service';

@Component({
  selector: 'app-comment-form',
  templateUrl: './comment-form.component.html',
  styleUrls: ['./comment-form.component.css']
})
export class CommentFormComponent implements OnInit {

  constructor(public commentService: CommentService) { }

  ngOnInit(): void {
  }

  onAddComment(form: NgForm){
    if(form.invalid){
      return;
    }

    this.commentService.addComment(form.value.title, form.value.content)
  }


}

