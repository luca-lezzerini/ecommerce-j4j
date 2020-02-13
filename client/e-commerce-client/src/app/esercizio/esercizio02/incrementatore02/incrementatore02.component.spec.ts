import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Incrementatore02Component } from './incrementatore02.component';

describe('Incrementatore02Component', () => {
  let component: Incrementatore02Component;
  let fixture: ComponentFixture<Incrementatore02Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Incrementatore02Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Incrementatore02Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
